/**
 * Copyright(C)2021, FPT University
 * SWP 391
 *
 * Record of change
 * DATE             VERSION             AUTHOR              DESCRIPTION
 * 2022-02-17         1.0               VUDMHE140017      First Implement
 */
package dao;

import interfaceDAO.IEncryptPasswordDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 *  The class is used to encrypt password when user  access to system throw account
 * @author vudm
 */
public class EncryptPasswordDAO implements IEncryptPasswordDAO{
    /**
     * Get random salt
     *
     * @return encryptedPassword
     * @throws NoSuchAlgorithmException, NoSuchProviderException
     * @throws java.security.NoSuchProviderException
     */
    @Override
    public byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        byte[] salt = null;
        try{
            //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        }catch(NoSuchAlgorithmException | NoSuchProviderException ex){
            throw ex;
        }
        return salt;
    }

    /**
     * The method used encrypt password
     *
     * @param salt are array byte
     * @param password is string
     * @return encryptedPassword
     * @throws java.security.NoSuchAlgorithmException
     */
    @Override
    public String encryptPassword(String password, byte[] salt) throws Exception {
        String encryptedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(password.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return encryptedPassword;
    }
}
