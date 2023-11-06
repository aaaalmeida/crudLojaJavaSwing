/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.HashMap;

/**
 *
 * @author arthu
 */
public interface Controller<Obj> {

    public Boolean adicionar(Obj obj);

    public Obj consulta(Integer id);

    public Boolean altera(Integer id, Object[] args);

    public Obj remove(Integer id);

    public HashMap<Integer, Obj> relatorio();
}
