package ru.itabrek.chguevent.service;

import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Iterator;
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
//            compressImage(filePath);
            return fileName;
        }
    }

    public void compressImage(String filePath) throws IOException {
        File input = new File(filePath);
        BufferedImage image = ImageIO.read(input);

        input.delete();

        File compressedImageFile = new File(filePath);
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.05f);  // Change the quality value you prefer
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
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
