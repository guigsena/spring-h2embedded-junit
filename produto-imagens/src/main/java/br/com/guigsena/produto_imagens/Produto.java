package br.com.guigsena.produto_imagens;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author Guilherme Sena
 *
 */
public @Data class Produto {

	/* ------------------------------
	 * ATRIBUTOS
	 * ------------------------------
	 */
    private int id;

    private String nome;
    
    private String descricao;
    
    public Produto produtoPai;

    public List<Produto> lstProdutosFilhos = new ArrayList<Produto>();
    
    public List<Imagem> lstImagens = new ArrayList<Imagem>();

    /* ------------------------------
	 * GET'S E SET'S
	 * ------------------------------
	 */
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getLstProdutosFilhos() {
		return lstProdutosFilhos;
	}

	public void setLstProdutosFilhos(List<Produto> lstProdutosFilhos) {
		this.lstProdutosFilhos = lstProdutosFilhos;
	}
	
	public Produto getProdutoPai() {
		return produtoPai;
	}

	public void setProdutoPai(Produto produtoPai) {
		this.produtoPai = produtoPai;
	}
	
	public List<Imagem> getLstImagens() {
		return lstImagens;
	}

	public void setLstImagens(List<Imagem> lstImagens) {
		this.lstImagens = lstImagens;
	}

	/* ------------------------------
	 * CONSTRUTOR
	 * ------------------------------
	 */
	public Produto() {
		super();
	}

	/* ------------------------------
	 * METODOS
	 * ------------------------------
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + "]";
	}
}
