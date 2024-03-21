package com.backend.DonateDrift.service;

import com.backend.DonateDrift.entity.Attachment;
import com.backend.DonateDrift.entity.CoverAttachment;
import com.backend.DonateDrift.entity.StatusAttachment;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class FileService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredentials();

    //private static long fo = 1;

    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cloud.json");
        return filePath.toString();
    }

    public static Attachment uploadImageToDrive(File file) throws GeneralSecurityException, IOException {
        Attachment attachment = new Attachment();
        //attachment.setId(fo++);
        //fo+=1;

        try {
            String folderId = "1Bb3khfeYMkWYAFtuNEyrYZfg4QkDrvPm";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.println("IMAGE URL: " + imageUrl);
            file.delete();
            attachment.setStatus(200);
            attachment.setMessage("Image Successfully Uploaded To Drive");
            attachment.setUrl(imageUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            attachment.setStatus(500);
            attachment.setMessage(e.getMessage());
        }
        return attachment;
    }

    public static CoverAttachment uploadImageToDriveCover(File file) throws GeneralSecurityException, IOException {
        CoverAttachment coverAttachment = new CoverAttachment();

        try {
            String folderId = "1Bb3khfeYMkWYAFtuNEyrYZfg4QkDrvPm";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl1 = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.println("IMAGE URL: " + imageUrl1);
            file.delete();
            coverAttachment.setStatus1(201);
            coverAttachment.setMessage1("Image Successfully Uploaded To Drive");
            coverAttachment.setUrl1(imageUrl1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            coverAttachment.setStatus1(501);
            coverAttachment.setMessage1(e.getMessage());
        }
        return coverAttachment;
    }

    public static StatusAttachment uploadImageToDriveStatus(File file) throws GeneralSecurityException, IOException {
        StatusAttachment statusAttachment = new StatusAttachment();

        try {
            String folderId = "1Bb3khfeYMkWYAFtuNEyrYZfg4QkDrvPm";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl1 = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.println("IMAGE URL: " + imageUrl1);
            file.delete();
            statusAttachment.setStatus2(202);
            statusAttachment.setMessage2("Image Successfully Uploaded To Drive");
            statusAttachment.setUrl2(imageUrl1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            statusAttachment.setStatus2(502);
            statusAttachment.setMessage2(e.getMessage());
        }
        return statusAttachment;
    }

    private static Drive createDriveService() throws GeneralSecurityException, IOException {

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }
}
