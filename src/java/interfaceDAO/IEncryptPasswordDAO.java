/**
 * Copyright(C)2021, FPT University
 * SWP 391
 *
 * Record of change
 * DATE             VERSION             AUTHOR              DESCRIPTION
 * 2022-02-14         1.0               VUDMHE140017      First Implement
 */
package interfaceDAO;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *  This interface class used to contain some method used to implements in another class
 * @author vudm
 */
public interface IEncryptPasswordDAO {
     /**
     * Get random salt
     *
     * @return encryptedPassword
     * @throws NoSuchAlgorithmException, NoSuchProviderException
     * @throws java.security.NoSuchProviderException
     */
    public byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException;
    
    /**
     * The method used encrypt password
     *
     * @param salt are array byte
     * @param password is string
     * @return encryptedPassword
     * @throws java.security.NoSuchAlgorithmException
     */
    public String encryptPassword(String password, byte[] salt) throws Exception;
}
