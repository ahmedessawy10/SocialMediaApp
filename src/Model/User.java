package Model;

public class User {
    private int id ; 
    private String username ; 
    private String password ; 
    private String email ; 
    private String Street ; 
    private String city ; 
    private String country  ;
    
    
    public void  setId(int id){ 
        this.id = id ; 
        
    }
    
    public int getId(){
        return this.id ; 
    }
    public void setName(String name ){
        this.username = name ; 
    }
    public String getName(){
        return this.username ; 
    }
    
    public void setEmail(String email){
        this.email  = email ; 

    }
    
    public String getEmail(){
        return this.email ; 
    }
    
    public void setStreet( String street){
        this.Street = street ;  
    }
    
    public String getStreet(){
        return this.Street ; 
    }
    
    public void setCity( String city ){
        this.city = city ; 
    }
    
    public String getCity(){
        return this.city  ; 
    }
    
    public void setCountry ( String country ){
        this.country = country  ; 
        
    }
    
    public String getCountry(){
        return this.country ; 

    }
    
    
    
    
    
}
