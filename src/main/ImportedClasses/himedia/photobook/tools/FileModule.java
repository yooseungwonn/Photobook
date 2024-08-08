package himedia.photobook.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public class FileModule {
   public String getOsName() {
      String os = System.getProperty("os.name").toLowerCase();
      return os;
   }

   public String saveFile(MultipartFile multipartFile, String path, String filename, String extName) throws IOException {
      byte[] fileData = multipartFile.getBytes();
      File saveFile = new File(path);
      if (!saveFile.exists()) {
         saveFile.mkdirs();
      }

      if (!extName.equals(".jpg")) {
         extName = ".jpg";
      }

      String finalFileName = filename + extName;
      String finalPath = saveFile.getPath() + "/";
      Path filePath = Paths.get(finalPath, finalFileName);
      Files.write(filePath, fileData, new OpenOption[0]);
      String os = System.getProperty("os.name").toLowerCase();
      if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
         Set<PosixFilePermission> perms = new HashSet();
         perms.add(PosixFilePermission.OWNER_READ);
         perms.add(PosixFilePermission.OWNER_EXECUTE);
         perms.add(PosixFilePermission.GROUP_READ);
         perms.add(PosixFilePermission.GROUP_EXECUTE);
         perms.add(PosixFilePermission.OTHERS_READ);
         perms.add(PosixFilePermission.OTHERS_EXECUTE);
         Files.setPosixFilePermissions(filePath, perms);
      }

      return finalFileName;
   }
}
