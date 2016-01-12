package andres;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

public class Andres 
{
    private static File carpetaDescargas;
    private static File carpetaImagenes;
    private static File carpetaDocumentos;
    private static File carpetaMusica;
    private static File carpetaVideos;
    private static File carpetaEscritorio;
    private static File carpetaGamez;
    private static File carpetaSoftware;
    private static File papelera;
    private static Scanner scanner;
    public static void main(String[] args) 
    {
        buscarUnidades();
        
        scanner = new Scanner(System.in);
        
        System.out.print("Que unidad es la que estas usando:");
        String strUnidadEnUso = scanner.nextLine();
        File unidadEnUso = new File(strUnidadEnUso + ":\\users");
        
        if(unidadEnUso.exists())
        {
            listarDirectorio(unidadEnUso);
            System.out.print("Que usuario estas usando??");
            String strUsuario = scanner.nextLine();
            
            carpetaDocumentos = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\Documents");
            carpetaImagenes = new File(unidadEnUso.getAbsolutePath() + ":\\" + strUsuario + "\\Pictures");
            carpetaVideos = new File(unidadEnUso.getAbsolutePath() + ":\\" + strUsuario + "\\Videos");
            carpetaMusica = new File(unidadEnUso.getAbsolutePath() + ":\\" + strUsuario + "\\Music");
            carpetaDescargas = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\Downloads");
            carpetaEscritorio = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\Desktop");
            carpetaGamez = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\gamez");
            carpetaSoftware = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\software");
            papelera = new File(unidadEnUso.getAbsolutePath() + "\\" + strUsuario + "\\trash");

            
            if(carpetaDescargas.list().length > 0)
            {
                System.out.println("En la carpeta Descargas tenes "+ carpetaDescargas.list().length + " elementos, por favor limpiala.");      
                ordenarCarpeta(carpetaDescargas);
            }
            if(carpetaEscritorio.list().length > 0 )
            {
                System.out.println("En la carpeta Descargas tenes "+ carpetaEscritorio.list().length + " elementos, por favor limpiala.");      
                ordenarCarpeta(carpetaEscritorio);
            }
            
            
            
        }
        else
        {
            System.out.println("No existe unidad en uso -> " + unidadEnUso);
        }
        
        
            
        
        
        
    }
    public static java.util.ArrayList<File> listarDirectorio(File directorio)
    {
        java.util.ArrayList<File> arr = new java.util.ArrayList<File>();
        
        String[] ficheros = directorio.list();
        
        for(int i = 0 ; i < ficheros.length ; i++)
        {
            System.out.println(directorio.getAbsolutePath() + "\\" +  ficheros[i]);
            //arr.add(new File(directorio.getName() + "\\" + ficheros[i]) );
        }
        
        return arr;
    }
    public static void ordenarCarpeta(File carpeta)
    {
        if(carpeta.exists())
        {
            String [] archivos = carpeta.list();
            for(int i = 0 ; i < archivos.length ; i++)
            {
                try 
                {
                    File fileAux = new File(carpeta.getAbsolutePath() + "\\" + archivos[i]);
                    System.out.println("Que hago con: " + fileAux + " ??");
                    System.out.println("1.Mover a Documentos");
                    System.out.println("2.Mover a Imagenes");
                    System.out.println("3.Mover a Musica");
                    System.out.println("4.Mover a Videos");
                    System.out.println("5.Mover a Software");
                    System.out.println("6.Mover a Games");
                    System.out.println("7.Papelera");
                    System.out.print("Opcion:");
                    int opcion = scanner.nextInt();
                    
                    
                    Path ruta1;
                    Path ruta2;
                    
                    switch(opcion)
                    {
                        case 1: 
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaDocumentos.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Documentos!");
                            }
                            ;break;
                        case 2: 
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaImagenes.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Imagenes!");
                            }
                            ;break;
                        case 3: 
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaMusica.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Musica!");
                            }
                            ;break;
                        case 4: 
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaVideos.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Videos!");
                            }
                            ;break;
                        case 5: 
                        
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaSoftware.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Software!");
                            }
                            
                            break;
                        case 6: 
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(carpetaGamez.getAbsolutePath() + "\\" + fileAux.getName());

                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Gamez!");
                            }    
                        case 7:
                            
                            ruta1 = Paths.get(fileAux.getAbsolutePath());
                            ruta2 = Paths.get(papelera.getAbsolutePath() + "\\" + fileAux.getName());
                            
                            if(Files.move(ruta1 , ruta2 , StandardCopyOption.REPLACE_EXISTING) != null)
                            {
                                System.out.println("Movido a Papelera!");
                            }
                            
                        break;
                    }
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void buscarUnidades()
    {
        File[] unidades = File.listRoots();
        Object it[] = new Object[unidades.length];
        
        for (int i=0;i<unidades.length;i++) 
        {
            String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
            String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);
            
            if(FileSystemView.getFileSystemView().isFloppyDrive(unidades[i]))
            {
                s1="Unidad de Disquete (A:)";
            }
            if(s1.equalsIgnoreCase(""))
            {
                s1 = s2;
            }
            
            System.out.println(unidades[i] + " " + s1 + " " + s2);
        } 
    }
}
