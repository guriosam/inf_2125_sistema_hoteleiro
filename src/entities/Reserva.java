package entities;

public class Reserva {
	
	private int codigoCliente;
	private int codigoQuarto;
	private String dataEntra;
	private String dataSai;
	private int numHospedes;
	private boolean fezCheckIn;
	
	public Reserva(int codigoCliente, int codigoQuarto, String dataEntra, String dataSai, int numHospedes,
			boolean fezCheckIn) {
		super();
		this.codigoCliente = codigoCliente;
		this.codigoQuarto = codigoQuarto;
		this.dataEntra = dataEntra;
		this.dataSai = dataSai;
		this.numHospedes = numHospedes;
		this.fezCheckIn = fezCheckIn;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public int getCodigoQuarto() {
		return codigoQuarto;
	}
	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}
	public String getDataEntra() {
		return dataEntra;
	}
	public void setDataEntra(String dataEntra) {
		this.dataEntra = dataEntra;
	}
	public String getDataSai() {
		return dataSai;
	}
	public void setDataSai(String dataSai) {
		this.dataSai = dataSai;
	}
	public int getNumHospedes() {
		return numHospedes;
	}
	public void setNumHospedes(int numHospedes) {
		this.numHospedes = numHospedes;
	}
	public boolean isFezCheckIn() {
		return fezCheckIn;
	}
	public void setFezCheckIn(boolean fezCheckIn) {
		this.fezCheckIn = fezCheckIn;
	}

	@Override
	public String toString() {
	
		return "Data de Entrada: " + dataEntra + "\n"
				+ "Data de Saída: " + dataSai + "\n"
				+ "Número de Hóspedes: " + numHospedes + "\n";		
	}
	
	
	
	
	/*
	Código do Cliente: 103
	Número do Quarto: 10
	Data de Entrada: Data Atual + cinco dias
	Data de Saída: Data Atual + sete dias
	Número de Hóspedes: 2
*/
	
	
			
			
	
}
