package com.kcj.management.shop.model;

import com.kcj.management.shop.model.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id @GeneratedValue
    private Long id;

    private String originalName;

    private String name;

    private String directory;

    private Long size;

    @OneToOne
    private Menu menu;

    public String initDirectory(MultipartFile file, Long id, String localDirectory){
        try {
            byte[] bytes = file.getBytes();
            int year = LocalDateTime.now().getYear();
            int month = LocalDateTime.now().getMonth().getValue();
            String strPath = localDirectory + java.io.File.separator
                    + year + java.io.File.separator
                    + month + java.io.File.separator
                    + id + file.getName() + file.getOriginalFilename();
            Path path = Paths.get(strPath);

            Files.createDirectories(path.getParent());

            Files.write(path, bytes);

            return strPath;
        } catch (IOException e){
            System.out.println("saveFile 에서 에러 발생");
        }
        return null;
    }
}
