package com.github.cc007.sleepypoweron;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WakeOnLan {

	public static final int PORT = 9;

	public static void wake(InetAddress ip, String mac) {
		try {
			Logger.getLogger(WakeOnLan.class.getName()).log(Level.INFO, "IP: " + ip);
			byte[] macBytes = getMacBytes(mac);
			byte[] bytes = new byte[6 + 16 * macBytes.length];
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) 0xff;
			}
			for (int i = 6; i < bytes.length; i += macBytes.length) {
				System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
			}

			DatagramPacket packet = new DatagramPacket(bytes, bytes.length, ip, PORT);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			socket.close();
			Logger.getLogger(WakeOnLan.class.getName()).log(Level.INFO, "Wake-on-LAN packet sent.");
		} catch (IllegalArgumentException | BindException ex) {
			Logger.getLogger(WakeOnLan.class.getName()).log(Level.SEVERE, "Failed to send Wake-on-LAN packet", ex);
		} catch (SocketException ex) {
			Logger.getLogger(WakeOnLan.class.getName()).log(Level.SEVERE, "Failed to send Wake-on-LAN packet", ex);
		} catch (IOException ex) {
			Logger.getLogger(WakeOnLan.class.getName()).log(Level.SEVERE, "Failed to send Wake-on-LAN packet", ex);
		}

	}

	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		if (hex.length != 6) {
			throw new IllegalArgumentException("Invalid MAC address.");
		}
		try {
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid hex digit in MAC address.");
		}
		return bytes;
	}

}
