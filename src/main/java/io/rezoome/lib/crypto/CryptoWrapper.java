package io.rezoome.lib.crypto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CryptoWrapper {

	private static Class<? extends CryptoManager> clazz = null;

	/**
	 * 
	 * Getting singleton instance of CryptoManager. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 * 
	 * @return
	 */
	public static CryptoManager getInstance() {
		CryptoManager instance = null;
		try {
			Method getInstance = clazz.getDeclaredMethod("getInstance");
			instance = (CryptoManager) getInstance.invoke(null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// Never occur.
			e.printStackTrace();
		}
		return instance;
	}

	private static boolean initialized = false;

	public synchronized static void init() {
		if (!initialized) {
			initialized = true;

			// Initialization.
		}
	}
}
