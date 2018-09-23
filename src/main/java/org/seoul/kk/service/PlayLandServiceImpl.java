package org.seoul.kk.service;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import org.seoul.kk.dto.RegisterPlayLandDto;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.Season;
import org.seoul.kk.exception.NotFoundPlayLand;
import org.seoul.kk.repository.PlayLandRepository;
import org.seoul.kk.service.s3.AwsS3Service;
import org.seoul.kk.util.S3KeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayLandServiceImpl implements PlayLandService {

    private PlayLandRepository playLandRepository;
    private AwsS3Service s3StorageService;

    @Autowired
    public PlayLandServiceImpl(PlayLandRepository playLandRepository,
                               AwsS3Service s3StorageService) {
        this.playLandRepository = playLandRepository;
        this.s3StorageService = s3StorageService;
    }

    //TODO change parameterType RegisterPlayLandDto to something.
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void registerPlayLand(RegisterPlayLandDto registerPlayLandDto, Traveler traveler) {
        Season season = Season.valueOf(registerPlayLandDto.getSeason());

        PlayLand playLand = PlayLand.builder()
                .traveler(traveler)
                .title(registerPlayLandDto.getTitle())
                .content(registerPlayLandDto.getContent())
                .season(season)
                .position(registerPlayLandDto.getPosition())
                .build();

        List<String> uploadResults = uploadPlayLandImageS3(registerPlayLandDto.getImages(), registerPlayLandDto.getTitle(), season, traveler.getId());
        StringBuilder sb = new StringBuilder();
        uploadResults.forEach(elem -> sb.append(elem).append(","));
        sb.setLength(sb.length() - 1);

        playLand.setImageUrl(sb.toString());

        playLandRepository.save(playLand);
    }

    @Override
    public PlayLand updatePlayLand(PlayLand requestBody) {
        PlayLand playLand = playLandRepository.findById(requestBody.getId()).orElseThrow(NotFoundPlayLand::new);
        playLand.setTitle(requestBody.getTitle());
        playLand.setContent(requestBody.getContent());
        playLand.setSeason(requestBody.getSeason());
        playLand.setPosition(requestBody.getPosition());

        return playLandRepository.save(playLand);
    }

    @Transactional
    @Override
    public void deletePlayLand(Long id) {
        PlayLand playLand = playLandRepository.findById(id).orElseThrow(NotFoundPlayLand::new);
        Arrays.stream(playLand.getImageUrl().split(",")).forEach(s3StorageService::deleteFile);
        playLandRepository.deleteById(id);
    }

    //TODO 파일 업로드 결과를 제어해야합니다.
    private List<String> uploadPlayLandImageS3(String base64EncodedData, String title, Season season, Long travelerId) {
        List<File> files = Lists.newArrayList();
        List<String> fileUrls = Lists.newArrayList();
        String[] datas = base64EncodedData.split(",");

        Arrays.stream(datas).forEach(elem -> {
            files.add(base64DecodingToFile(elem,  Base64.encodeBase64URLSafeString(title.trim().getBytes()) + "-" + files.size() + ".png"));
        });

        files.forEach(file -> {
            fileUrls.add(s3StorageService.uploadFile(S3KeyHelper.generateS3ObjectKey(travelerId, season, file.getName()), file));
            file.delete();
        });

        return fileUrls;
    }

    //TODO util class를 만들어 메소드를 이 클래스에서 분리합니다.(SRP 위배되었기 때문)
    private File base64DecodingToFile(String data, String fileName) {
        byte[] binary = Base64.decodeBase64(data);
        return convertByteArraysToFile(fileName, binary);
    }

    //TODO util class를 만들어 메소드를 이 클래스에서 분리합니다.(SRP 위배되었기 때문)
    private File convertByteArraysToFile(String fileName, byte[] content) {
        File file = new File(fileName);
        BufferedOutputStream writer = null;
        try {
            writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

}
