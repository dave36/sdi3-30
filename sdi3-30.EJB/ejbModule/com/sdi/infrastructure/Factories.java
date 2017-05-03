package com.sdi.infrastructure;

import com.sdi.persistence.Persistence;

public class Factories {
	
	private static String CONFIG_FILE = "/factories.properties";
	
	public static ServiceFactory services = (ServiceFactory)
			FactoriesHelper.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
	
	public static Persistence persistence = (Persistence)
			FactoriesHelper.createFactory(CONFIG_FILE, "PERSISTENCE_FACTORY");
}
