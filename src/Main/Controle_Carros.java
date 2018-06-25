package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle_Carros {

    List<Contato_Carros> lista = new ArrayList<>();

    public Controle_Carros() {
   
    }

    public Contato_Carros buscar(int chave) {
        for (int i = 0; i < lista.size(); i++) {
            if (chave==lista.get(i).getNomeDoCarro()) {
                return lista.get(i);//se encontrou, retorna a linha toda (um contato)
            }
        }
        return null; //se não encontrou na lista, retorna um contato nulo
    }

    public void inserir(Contato_Carros contato) {
        lista.add(contato);
    }

    void alterar(Contato_Carros contatoOriginal, Contato_Carros contatoAlterado) {
        lista.set(lista.indexOf(contatoOriginal), contatoAlterado);
    }

    public List<String> listar() {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            ls.add(""
                    + lista.get(i).getNomeDoCarro() + ";"
                    + lista.get(i).getMarca() + ";"
                    + lista.get(i).getModelo() + ";"
                    + lista.get(i).getData()
            );
        }
        return ls;
    }
    
    public void excluir(Contato_Carros contato){
        lista.remove(contato);
    }

}
