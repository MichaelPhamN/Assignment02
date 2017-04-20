import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Test of CS 143 Assignment 2 by Martin Hock (Version of 12:30 AM 4/19/2017)
 * 
 * You may only use this program as a student of Martin Hock, CS 143 Spring 2017.
 */

public class RecursionIntroTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		int[] ns = { 0, 1, 2, 3, 4, 5, 10, 20, 100, 1000, 10000, 100000000, 700000000, 800000000, 1000000000 };
		int[] fibbys = { 1, 2, 3, 4, 6, 6, 11, 24, 111, 1125, 11497, 113764606, 791169897, 918443045, 1146120114 };
		int[] scores = { 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 5, 5 };
		int fibbyscore = 0;
		int tgscore = 0;
		int stgscore = 0;
		PrintStream out = System.out;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String source = null;
		try {
			source = new String(Files.readAllBytes(Paths.get("src" + File.separator + "RecursionIntro.java")));
		} catch (Exception e) {
			System.out.println(
					"Couldn't find RecursionIntro.java! Run this from the same Eclipse project as RecursionIntro.");
			return;
		}
		if (source.matches("(?s).*\\sfor[\\s\\(].*")) {
			System.out.println(
					"Detected 'for' statement in your program! Please remove anything that resembles a 'for'.");
			System.exit(-1);
		}
		if (source.matches("(?s).*\\swhile[\\s\\(].*")) {
			System.out.println(
					"Detected 'while' statement in your program! Please remove anything that resembles a 'while'.");
			System.exit(-1);
		}
		if (source.matches("(?s).*\\simport\\s.*") || source.indexOf("import") == 0) {
			System.out.println("Detected 'import' statement in your program! Please remove any word 'import'.");
			System.exit(-1);
		}

		for (int dotIndex = source.indexOf('.'); dotIndex != -1; dotIndex = source.indexOf('.', dotIndex + 1)) {
			if ("System.out.println".equals(source.substring(dotIndex - 6, dotIndex + 12))
					|| "System.out.println".equals(source.substring(dotIndex - 10, dotIndex + 8))
					|| "length".equals(source.substring(dotIndex + 1, dotIndex + 7))) {
				continue;
			}
			System.out.println("Bad dot! Context: " + source.substring(dotIndex - 10, dotIndex + 10));
			System.exit(-1);
		}
		try {
			System.out.println("If you don't see a score, your program didn't get to the end!");
			System.out.println("Testing fibby...");
			for (int i = 0; i < ns.length; i++) {
				long fib = RecursionIntro.fibby(ns[i]);
				if (fib != fibbys[i]) {
					System.out.println("Expected fibby(" + ns[i] + ") = " + fibbys[i] + " but got " + fib);
				} else
					fibbyscore += scores[i];
			}
		} finally {
			System.out.println("fibby score: " + fibbyscore + "/30");
		}
		try {
			System.out.println("Testing tablegen...");
			int[] starts = { 1, 1000 };
			int[] ends = { 100, 1020 };
			byte[][] md5s = { { -85, 26, 69, 63, 114, -104, 25, 120, -114, 69, 64, 119, -116, 122, -113, -31 },
					{ -45, -110, 87, 94, 114, 123, 121, 1, -108, 5, -10, -96, 102, 91, 79, -86 } };
			for (int i = -1; i < starts.length; i++) {
				ByteArrayOutputStream ba = new ByteArrayOutputStream();
				System.setOut(new PrintStream(ba));
				if (i == -1) {
					RecursionIntro.tablegen(10, 15);
					System.setOut(out);
					String expected = "10 11\n11 14\n12 15\n13 15\n14 15\n15 18\n";
					if (!Arrays.equals(expected.split("\\r?\\n"), ba.toString().split("\\r?\\n"))) {
						System.out.println("For tablegen(10, 15), expected:\n" + expected + "\n, got: \n" + ba);
					} else {
						tgscore += 20;
					}
				} else {
					RecursionIntro.tablegen(starts[i], ends[i]);
					System.setOut(out);
					byte[] digest = md5.digest(Arrays.toString(ba.toString().split("\\r?\\n")).getBytes());
					if (!Arrays.equals(md5s[i], digest)) {
						System.out
								.println("Didn't get expected output for tablegen(" + starts[i] + ", " + ends[i] + ")");
						System.out.println("Saw this:");
						System.out.println(ba);
						System.out.println("MD5 sum of output seen: " + Arrays.toString(digest));
					} else
						tgscore += 10;
				}
			}
		} finally {
			System.setOut(out);
			System.out.println("tablegen score: " + tgscore + "/40");
		}
		try {
			System.out.println("Testing sparsetablegen...");
			int[] starts = { 1, 1000 };
			int[] ends = { 100, 1020 };
			byte[][] md5s = { { 58, 46, 104, 125, -11, -80, -18, 110, -37, -96, 106, 87, -22, 122, 72, 64 },
					{ 19, 45, 112, -113, -33, 77, 114, 123, -71, -95, -38, 101, 59, 57, 45, -54 } };
			for (int i = -1; i < starts.length; i++) {
				ByteArrayOutputStream ba = new ByteArrayOutputStream();
				System.setOut(new PrintStream(ba));
				if (i == -1) {
					RecursionIntro.sparsetablegen(13, 20);
					System.setOut(out);
					String expected = "13 15\n15 18\n16 21\n20 24\n";
					if (!Arrays.equals(expected.split("\\r?\\n"), ba.toString().split("\\r?\\n"))) {
						System.out.println("For sparsetablegen(10, 15), expected:\n" + expected + "\n, got: \n" + ba);
					} else {
						stgscore += 10;
					}
				} else {
					RecursionIntro.sparsetablegen(starts[i], ends[i]);
					System.setOut(out);
					byte[] digest = md5.digest(Arrays.toString(ba.toString().split("\\r?\\n")).getBytes());
					if (!Arrays.equals(md5s[i], digest)) {
						System.out.println(
								"Didn't get expected output for sparsetablegen(" + starts[i] + ", " + ends[i] + ")");
						System.out.println("Saw this:");
						System.out.println(ba);
						System.out.println("MD5 sum of output seen: " + Arrays.toString(digest));
					} else
						stgscore += 10;
				}
			}
		} finally {
			System.out.println("sparsetablegen score: " + stgscore + "/30");
			System.out.println("Total score: " + (fibbyscore + tgscore + stgscore) + "/100");
			System.out.println(
					"Your score may be affected by academic dishonesty. Additional examples may be tested. Extra credit not tested (but if fibby test runs fast, good job!)");
		}
	}

}
