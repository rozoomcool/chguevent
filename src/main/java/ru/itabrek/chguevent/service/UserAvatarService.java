package ru.itabrek.chguevent.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class UserAvatarService {

    // Путь, где будут храниться загруженные изображения
    private static final String UPLOAD_DIR = "upload-user-avatar-dir/";

    public String saveAvatar(byte[] imageData) throws IOException {
        // Создаем директорию, если она не существует
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Генерируем уникальное имя файла для изображения (можно использовать UUID или timestamp)
        String fileName = UUID.randomUUID().toString() + ".jpg";

        // Полный путь к файлу
        String filePath = UPLOAD_DIR + fileName;

        // Сохраняем изображение на диск
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageData);
            return fileName;
        }
    }

    public byte[] findAvatar(String fileName) throws IOException {
        // Прочитайте сырые данные изображения из файла
        String filePath = UPLOAD_DIR + fileName;
        File imageFile = new File(filePath);
        byte[] imageData = Files.readAllBytes(imageFile.toPath());

        return imageData;
    }

    public void deleteAvatar(String fileName){
        String filePath = UPLOAD_DIR + fileName;
        File imageFile = new File(filePath);
        if(imageFile.exists()){
            imageFile.delete();
        }
    }
}
