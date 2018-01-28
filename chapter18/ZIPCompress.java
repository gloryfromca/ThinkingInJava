package chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPCompress {

	public static void main(String[] args) throws IOException {
		File fzip = new File(FilesForTest.zipMac);
		FileOutputStream fos = new FileOutputStream(fzip);
		CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(cos);
		BufferedOutputStream bos = new BufferedOutputStream(zos);
		zos.setComment("a test for java zip");
		args = new String[] { FilesForTest.s1Mac, FilesForTest.s2Mac };

		for (String f : args) {
			BufferedReader in = new BufferedReader(new FileReader(f));
			zos.putNextEntry(new ZipEntry(f));
			int c;
			while ((c = in.read()) != -1) {
				bos.write(c);
			}
			in.close();
			bos.flush();
		}
		bos.close();
		System.out.println("CheckSum: " + cos.getChecksum().getValue());

		// read files from zip-file.
		FileInputStream fis = new FileInputStream(fzip);
		CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
		ZipInputStream zis = new ZipInputStream(cis);
		BufferedInputStream bis = new BufferedInputStream(zis);
		ZipEntry ze;
		while ((ze = zis.getNextEntry()) != null) {
			System.out.println("Reading file: " + ze);
			int c;
			while ((c = bis.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
		}
		zis.close();
		System.out.println(cis);

		// another way to read zip-file doesn't work.
		System.out.println("another way to read zip-file");
		fis = new FileInputStream(fzip);
		cis = new CheckedInputStream(fis, new Adler32());
		zis = new ZipInputStream(cis);
		bis = new BufferedInputStream(zis);
		int c;
		while ((c = bis.read()) != -1) {
			System.out.print((char) c);
		}

		// Enumeration of ZipFile.
		ZipFile zf = new ZipFile(fzip);
		Enumeration e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) e.nextElement();
			System.out.println("file: " + zipEntry.getName());
			System.out.println(zipEntry.getComment());
			System.out.println(zipEntry.getExtra());
			System.out.println(zipEntry.getCompressedSize());
		}

	}

}
