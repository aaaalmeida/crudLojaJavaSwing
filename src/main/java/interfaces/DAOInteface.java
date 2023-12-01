/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.HashMap;

/**
 *
 * @author arthu
 */
public interface DAOInteface<T> {

    public Boolean adicionar(T obj);

    public T consultaPorId(Integer id);

    public Boolean altera(Integer id, Object[] args);

    public T remove(Integer id);

    public HashMap<Integer, T> relatorio();
}
