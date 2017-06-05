package br.com.guigsena.produto_imagens;

import lombok.Data;

/**
 * 
 * @author Guilherme Sena
 *
 */
public @Data class Imagem {

	/* ------------------------------
	 * ATRIBUTOS
	 * ------------------------------
	 */
    private int id;

    private String tipo;
    
    private Produto produto;
    
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/* ------------------------------
	 * CONSTRUTORES
	 * ------------------------------
	 */
	public Imagem() {
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
		Imagem other = (Imagem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + "]";
	}
	
}
