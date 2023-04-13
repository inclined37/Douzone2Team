package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.User;

public class FileUtil 
{
   
   public static void fileUpload(String path, List<? extends Object> dataList)
   {
      File file = new File(path);
      FileOutputStream fos = null;
      ObjectOutputStream ois = null;
      try
      {
         fos = new FileOutputStream(file, false);
         ois = new ObjectOutputStream(fos);
         ois.writeObject(dataList);
      } 
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally 
      {
         try 
         {
            fos.close();
            ois.close();
         } 
         catch (IOException e) 
         {
            e.printStackTrace();
         }
      }
   }   
   
   public static void fileUpload(String path, Map<String, ? extends Object> dataList)
   {
      File file = new File(path);
      FileOutputStream fos = null;
      ObjectOutputStream ois = null;
      try
      {
         fos = new FileOutputStream(file, false);
         ois = new ObjectOutputStream(fos);
         ois.writeObject(dataList);
      } 
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally 
      {
         try 
         {
            fos.close();
            ois.close();
         } 
         catch (IOException e) 
         {
            e.printStackTrace();
         }
      }
   }   
   
   public static List<? extends Object> fileDownload(String path)
   {
      File file = new File(path);
      try
      {
         FileInputStream fis = new FileInputStream(file);
         ObjectInputStream oos = new ObjectInputStream(fis);
         
         @SuppressWarnings("unchecked")
         List<? extends Object> LV_dataList = (List<? extends Object>) oos.readObject();

         oos.close();
         fis.close();
         
         return LV_dataList;
      }
      catch(Exception e)
      {
         System.out.println("불러오는데 실패하였습니다.");
         e.printStackTrace();
         return null;
      }
   }
   
   public static Map<String, User> userFileDownload(String path)
   {
      File file = new File(path);
      try
      {
         FileInputStream fis = new FileInputStream(file);
         ObjectInputStream oos = new ObjectInputStream(fis);
         
         @SuppressWarnings("unchecked")
         Map<String, User> LV_dataMap = (HashMap) oos.readObject();

         oos.close();
         fis.close();
         
         return LV_dataMap;
      }
      catch(Exception e)
      {
         System.out.println("불러오는데 실패하였습니다.");
         e.printStackTrace();
         return null;
      }
   }
   
}