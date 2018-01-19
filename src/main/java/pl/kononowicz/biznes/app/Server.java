/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kononowicz.biznes.app;

/**
 *
 * @author alexp
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "servers")
public class Server {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private String server_name;
    private String host;
    private String db;
    private String username;
    private String password;
    private Integer port;
    private String socket;
    private String wrapper;
    private String owner;
    
        public Server(String server_name,
                 String host,
                 String db,
                 String username,
                 String password,
                 String socket,
                 String wrapper,
                 String owner,
                 Integer port) {
                
                  this.server_name = server_name;
                 this.host = host;
                 this.db = db;
                 this.username = username;
                 this.password = password;
                 this.socket = socket;
                 this.wrapper = wrapper;
                 this.owner = owner;
                 this.port =  port;
                
        }

        public String getServer() {
                return server_name;
        }
        
        public String getHost() {
                return host;
        }
        
        public String getDb() {
                return db;
        }
        
        public String getUsername() {
                return username;
        }
        
        public String getPassword() {
                return password;
        }
        
        public Integer getPort() {
                return port;
        }
        
        public String getSocket() {
                return socket;
        }
        
        public String getWrapper() {
                return wrapper;
        }
        
        public String getOwner() {
                return owner;
        }
}
