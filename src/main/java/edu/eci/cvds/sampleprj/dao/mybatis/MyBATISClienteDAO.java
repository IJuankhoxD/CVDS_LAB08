/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2152030
 */
public class MyBATISClienteDAO implements ClienteDAO {
    @Inject
    private ClienteMapper clienteMapper;    


    @Override
    public Cliente load(int id) throws PersistenceException {
    try{
        return clienteMapper.consultarCliente(id);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al consultar el item "+id,e);
    }


    }

    @Override
    public void save(Cliente cl) throws PersistenceException {                   
        try{
            clienteMapper.agregarCliente(cl);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }
        
    }

    
}
