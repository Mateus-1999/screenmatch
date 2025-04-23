package br.com.alura.screenmatch.model;

public enum Categoria {
	ACAO("Action", "Ação"),
	ROMANCE("Romance", "Romance"),
	COMEDIA("Comedy", "Comédia"),
	DRAMA("Drama", "Drama"),
	CRIME("Crime", "Crime");
	
	private String CategoriaOmdb;
	
	private String categoriaPortugues;
	
	Categoria(String categoriaOmdb, String categoriaPortugues){
		this.CategoriaOmdb = categoriaOmdb;
		this.categoriaPortugues = categoriaPortugues;
	}
	
	public static Categoria fromString(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.CategoriaOmdb.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: ");
	}
	
	public static Categoria fromPortugues(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: ");
	}
}
