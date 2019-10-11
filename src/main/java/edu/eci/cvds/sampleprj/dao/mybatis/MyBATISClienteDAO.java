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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2152030
 */
public class MyBATISClienteDAO implements ClienteDAO {
    @Inject
    private ClienteMapper clienteMapper;    


    @Override
    public Cliente load(long id) throws PersistenceException {
    try{
        return clienteMapper.consultarCliente(id);
    }
    catch(org.apache.ibatis.exceptions.PersistenceException e){
        throw new PersistenceException("Error al consultar el cliente "+id,e);
    }


    }

    @Override
    public void save(Cliente cl) throws PersistenceException {                   
        try{
            clienteMapper.agregarCliente(cl);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el cliente "+cl.toString(),e);
        }
        
    }

    @Override
    public List<Cliente> load() throws PersistenceException {
        try{
        return clienteMapper.consultarClientes();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los clientes ",e);
        }
    }

    @Override
    public void saveItemRentado(long clid, int itid, Date fechaIni, int numeroDias) throws PersistenceException {
        Calendar calendario=Calendar.getInstance();
        calendario.setTime(fechaIni);
        calendario.add(Calendar.DAY_OF_YEAR, numeroDias);
        Date fechaFin=calendario.getTime();
        
        clienteMapper.agregarItemRentadoACliente(clid,itid,fechaIni,fechaFin);
    }

    
}
