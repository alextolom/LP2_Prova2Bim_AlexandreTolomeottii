
package Main;

/**
 *
 * @author radames
 */
public class Contato {
    private int id;
    private String nome;
    private String endereco;

    public Contato() {
    }

    public Contato(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id 
                + ", nome=" + nome 
                + ", endereco=" + endereco + '}';
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

   
    
    
}
