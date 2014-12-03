package org.wahlzeit.model.extension;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoCase;

public class PancakePhotoCase extends PhotoCase {

	public PancakePhotoCase(Photo myPhoto) {
		super(myPhoto);
		// TODO Auto-generated constructor stub
	}

	public PancakePhotoCase(ResultSet rset) throws SQLException {
		super(rset);
		// TODO Auto-generated constructor stub
	}

}
