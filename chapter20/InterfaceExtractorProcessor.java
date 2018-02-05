package chapter20;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;

public class InterfaceExtractorProcessor implements AnnotationProcessor {
	private final AnnotationProcessorEnvironment env;
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<MethodDeclaration>();

	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	@Override
	public void process() {
		for (TypeDeclaration clsDecl : env.getSpecifiedTypeDeclarations()) {
			ExtractInterface annot = clsDecl.getAnnotation(ExtractInterface.class);
			if (annot == null)
				break;
			for (MethodDeclaration clsmd : clsDecl.getMethods()) {
				if (clsmd.getModifiers().contains(Modifier.PUBLIC) && !clsmd.getModifiers().contains(Modifier.STATIC))
					interfaceMethods.add(clsmd);
				if (interfaceMethods.size() > 0) {
					PrintWriter writer;
					try {
						writer = env.getFiler().createSourceFile(annot.value());
						writer.println("package " + clsDecl.getPackage().getQualifiedName() + ";");
						writer.print("public interface " + annot.value() + "{ ");
						for (MethodDeclaration m : interfaceMethods) {
							writer.print(" public ");
							writer.print(m.getReturnType() + " ");
							writer.print(m.getSimpleName() + " (");
							int i = 0;
							for (ParameterDeclaration pd : m.getParameters()) {
								writer.print(pd.getType() + " " + pd.getSimpleName());
								if (++i < m.getParameters().size())
									writer.print(", ");

							}
							writer.println(");");
						}
						writer.print("};");
						writer.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

				}

			}

		}

	}

}
