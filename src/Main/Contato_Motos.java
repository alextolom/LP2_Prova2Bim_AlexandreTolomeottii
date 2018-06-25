
package Main;

/**
 *
 * @author radames
 */
public class Contato_Motos {
    private int NomeDoCarro;
    private String Marca;
    private String Modelo;
    private String Data;

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Contato_Motos() {
    }

    public Contato_Motos(int NomeDoCarro, String nome, String endereco, String data) {
        this.NomeDoCarro = NomeDoCarro;
        this.Marca = nome;
        this.Modelo = endereco;
        this.Data = data;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + NomeDoCarro 
                + ", nome=" + Marca 
                + ", endereco=" + Modelo 
                + ", Data=" + Data +'}';
    }

    
    
    public int getNomeDoCarro() {
        return NomeDoCarro;
    }

    public void setNomeDoCarro(int NomeDoCarro) {
        this.NomeDoCarro = NomeDoCarro;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

   
    
    
}
