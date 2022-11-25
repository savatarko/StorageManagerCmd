package cmd;

import exceptions.StorageAlreadyInitializedException;
import spec.Configuration;
import spec.MyFile;
import spec.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        try {
            if (input.equalsIgnoreCase("gdrive")) {
                Class.forName("code.GDrive");
            }
            else if(input.equalsIgnoreCase("local")){
                Class.forName("LocalDrive");
            }
            else{
                System.out.println("wrong input");
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        StorageManager sm = StorageManager.getSm();

        System.out.println("New or existing storage? 1 or 2:");
        input = scanner.nextLine();
        if(input.equalsIgnoreCase("1")) {
            System.out.println("path?");
            String path = scanner.nextLine();
            System.out.println("default or your config? 1 for default, !=1 for custom");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("1")) {
                sm.CreateStorage(path);
            }
            else{
                System.out.println("Parameters? (max size and allowed extensions)");
                input = scanner.nextLine();
                String[] split = input.split(" ");
                sm.CreateStorage(new Configuration(Long.parseLong(split[0]), split[1]), path);
            }
        }
        else if (input.equalsIgnoreCase("2"))
        {
            System.out.println("path?");
            input = scanner.nextLine();
            sm.LoadStorage(input);
        }
        else return;

        input = scanner.nextLine();
        String[] split;
        List<MyFile> files;
        List<String> collection = new ArrayList<>();
        List<String> output = new ArrayList<>();
        while(!input.equalsIgnoreCase("-1"))
        {
            Integer i = Integer.parseInt(input);
           switch (i){
               case 1:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   if(split.length == 2)
                   {
                       sm.CreateDirectory(split[0], split[1]);
                   }
                   else sm.CreateDirectory(split[0], split[1], Integer.parseInt(split[2]));
                   break;
               case 2:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   sm.CreateDirectoryBash(split[0], split[1]);
                   break;
               case 3:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   File file = new File(split[1]);
                   sm.StoreFile(split[0], new MyFile(file));
                   break;
               case 4:
                   input = scanner.nextLine();
                   sm.DeleteFromStorage(input);
                   break;
               case 5:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   sm.MoveFile(split[0], split[1]);
                   break;
               case 6:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   sm.DownloadFile(split[0], split[1]);
                   break;
               case 7:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   sm.Rename(split[0], split[1]);
                   break;
               case 8:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   files = sm.GetFiles(split[0]);
                   for(int it = 1;i<split.length;i++)
                   {
                       if(split[it].contains(","))
                       {
                           output = sm.FilterResult(files, split[it]);
                       }
                       else{
                           files = sm.SortResult(files, split[it]);
                       }
                   }
                   if(output.size() == 0) {
                       for (var f : files) {
                           System.out.println(f);
                       }
                   }
                   else{
                       for (var f : output) {
                           System.out.println(f);
                       }
                       output.clear();
                   }
                   break;
               case 9:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   files = sm.GetAllFiles(split[0]);
                   for(int it = 1;i<split.length;i++)
                   {
                       if(split[it].contains(","))
                       {
                           output = sm.FilterResult(files, split[it]);
                       }
                       else{
                           files = sm.SortResult(files, split[it]);
                       }
                   }
                   if(output.size() == 0) {
                       for (var f : files) {
                           System.out.println(f);
                       }
                   }
                   else{
                       for (var f : output) {
                           System.out.println(f);
                       }
                       output.clear();
                   }
                   break;
               case 10:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   files = sm.GetAllSubFiles(split[0]);
                   for(int it = 1;i<split.length;i++)
                   {
                       if(split[it].contains(","))
                       {
                           output = sm.FilterResult(files, split[it]);
                       }
                       else{
                           files = sm.SortResult(files, split[it]);
                       }
                   }
                   if(output.size() == 0) {
                       for (var f : files) {
                           System.out.println(f);
                       }
                   }
                   else{
                       for (var f : output) {
                           System.out.println(f);
                       }
                       output.clear();
                   }
                   break;
               case 11:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   files = sm.GetFilesType(split[0]);
                   for(int it = 1;i<split.length;i++)
                   {
                       if(split[it].contains(","))
                       {
                           output = sm.FilterResult(files, split[it]);
                       }
                       else{
                           files = sm.SortResult(files, split[it]);
                       }
                   }
                   if(output.size() == 0) {
                       for (var f : files) {
                           System.out.println(f);
                       }
                   }
                   else{
                       for (var f : output) {
                           System.out.println(f);
                       }
                       output.clear();
                   }
                   break;
               case 12:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   files = sm.GetFilesNamed(split[0]);
                   for(int it = 1;i<split.length;i++)
                   {
                       if(split[it].contains(","))
                       {
                           output = sm.FilterResult(files, split[it]);
                       }
                       else{
                           files = sm.SortResult(files, split[it]);
                       }
                   }
                   if(output.size() == 0) {
                       for (var f : files) {
                           System.out.println(f);
                       }
                   }
                   else{
                       for (var f : output) {
                           System.out.println(f);
                       }
                       output.clear();
                   }
                   break;
               case 13:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   collection.clear();
                   for(int it = 1;i<split.length;i++)
                   {
                       collection.add(split[it]);
                   }
                   System.out.println(sm.IsContained(split[0], collection));
                   break;
               case 14:
                   input = scanner.nextLine();
                   System.out.println(sm.Locate(input));
                   break;
               case 15:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   if(split.length == 3)
                       files = sm.GetFilesTimeCreated(split[0], split[1], split[2]);
                   else files = sm.GetFilesTimeCreated(split[0], split[1]);
                   for(var f : files){
                       System.out.println(f);
                   }
                   break;
               case 16:
                   input = scanner.nextLine();
                   split = input.split(" ");
                   if(split.length == 3)
                       files = sm.GetFilesTimeModified(split[0], split[1], split[2]);
                   else files = sm.GetFilesTimeModified(split[0], split[1]);
                   for(var f : files){
                       System.out.println(f);
                   }
                   break;
               default:
                   System.out.println("wrong num");
                   break;
           }
           input = scanner.nextLine();
        }
    }
}
