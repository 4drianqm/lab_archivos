/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivos_lab;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class Main {

    private void writeData(String name,int phone,byte age){
        try {
            RandomAccessFile archivo = new RandomAccessFile("agenda1.bin","rw");
            
            archivo.seek(archivo.length());
            archivo.write(name.length());
            archivo.writeBytes(name);
            archivo.write(age);
            archivo.writeInt(phone);
            archivo.writeBytes("#");
            
            
           
           archivo.close();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void readData(){
        
        try {
            RandomAccessFile archivo = new RandomAccessFile("agenda1.bin","r");
            
            int tamanio = archivo.read();
            
            
            while(tamanio != -1){
                byte data[] = new byte[tamanio];
                archivo.read(data);
                byte age = archivo.readByte();
                int phone = archivo.readInt();
                byte separador[] = new byte[1];
                archivo.read(separador);
                System.out.println("Nombre: "+ new String(data));
                System.out.println("Edad: " + age);
                System.out.println("Tel: "+ phone);
                System.out.println("separador: "+ new String(separador));
                
                tamanio = archivo.read();
            }
            
            
            archivo.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void WriteTam(String name,int phone,byte age){
        try {
            RandomAccessFile archivo = new RandomAccessFile("agenda2.bin","rw");
            
            archivo.seek(archivo.length());
            
            archivo.writeBytes(name);
            archivo.write(age);
            archivo.writeInt(phone);
            
            
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void readTam(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("agenda2.bin","r");
            
            
            
            byte data[] = new byte[30];
            
            while(archivo.read(data) != -1){
                byte age = archivo.readByte();
                int phone = archivo.readInt();
                
                System.out.println("Nombre: " +  new String(data));
                System.out.println("Edad: "+age);
                System.out.println("Tel: "+ phone);
            }
            
            
            
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public static void main(String[] args) {
        // TODO code application logic here
        Main main = new Main();
        byte menu;
        char[] nombre = new char[30];
        Scanner read = new Scanner(System.in);
        
        for(int i = 0;i<30;i++){
            nombre[i] = '0';
        }
        
        do{
            
            System.out.println("====== MENU ======");
            System.out.println("1.Agregar");
            System.out.println("2.Mostrar (separador binario)");
            System.out.println("3.Mostrar (por tamanio)");
            System.out.println("4.SALIR");
            System.out.println("Opccion: ");
            menu = read.nextByte();
            
            
            if(menu == 1){
                read.nextLine();
                System.out.println("Nombre: ");
                String name = read.nextLine();
                System.out.println("Edad: ");
                byte age = read.nextByte();
                System.out.println("Telefono: ");
                int phone = read.nextInt();
                
                for(int i=0;i<name.length();i++){
                    nombre[i] = name.charAt(i);
                }
                
                String name2 = name;
                name = String.valueOf(nombre);
                
                main.writeData(name2, phone, age);
                main.WriteTam(name, phone, age);
                
            }
            else if(menu == 2){
                main.readData();
            }
            else if(menu == 3){
                main.readTam();
            }
            
            
        }while(menu != 4);
        
        
    }
    
}
