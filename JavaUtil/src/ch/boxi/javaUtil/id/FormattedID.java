package ch.boxi.javaUtil.id;

public class FormattedID extends PrefixedID{
	private static final long serialVersionUID = -2550885611584416746L;

	protected final String format;
		
	/**
	 * format like {prefix|-}0##.###-###
	 * where: 
	 * 	-  {prefix} will be replaced with the prefix and every thing behind the | in {prefx|ABC} is only printed if a prefix is set.
	 *  -  0 marks that the number is filled with leading zeros
	 *  -  # marks a singel digit
	 *  -  9 marks the place where to print the offcut
	 *  
	 *  Exp: 
	 *     format: "{prefix|-}0##.###.###"
	 *     prefix: "PID"
	 *     dbRepresentive: "1234"
	 *     \=> PID-000.001.234
	 *     
	 *     format: "{prefix|-}###.###.###"
	 *     prefix: ""
	 *     dbRepresentive: "1234"
	 *     \=> .1.234
	 *     
	 *     format: "{prefix}-##.##.9"
	 *     prefix: "PID"
	 *     dbRepresentive: "123456789"
	 *     \=> PID-12.34.56789
	 *     
	 *     @param prefix			value to identify the type of the ID
	 *     @param dbRepresentive 	primary key from DB
	 *     @param format			as described above
	 */
	public FormattedID(String prefix, long dbRepresentive, String format) {
		super(prefix, dbRepresentive);
		this.format = format;
	}
	
	@Override
	public String toString(){
		return format;
	}

	@Override
	public boolean isValid(long id) {
		return Long.toString(dbRepresentiv).length() <= FormatHelper.countDigits(format);
	}
	
	

}
