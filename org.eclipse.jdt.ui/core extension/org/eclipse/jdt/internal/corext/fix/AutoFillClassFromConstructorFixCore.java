/*******************************************************************************
 * Copyright (c) 2026 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.corext.fix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class AutoFillClassFromConstructorFixCore extends CompilationUnitRewriteOperationsFixCore{
	//Constraint: it looks for fields with the same name
	//Maybe if the lsit of fields is not null, we just don't propose it?
	//What we need list of fields
	//we need to check if the field name match the constructor name
	//
	private static class ConstructorClassAnalyzer extends ASTVisitor{
		FieldDeclaration[] fFields;
		@Override
		public boolean visit(TypeDeclaration node) {
			if(node.getFields() != null) {
				fFields = node.getFields();
			}
			return true;
		}

		public FieldDeclaration[] getFieldDeclarations() {
			return fFields;
		}
	}

	public AutoFillClassFromConstructorFixCore(String name, CompilationUnit compilationUnit, CompilationUnitRewriteOperation operation) {
		super(name, compilationUnit, operation);
	}

	public static AutoFillClassFromConstructorFixCore createAutoFillFromConstructorFixCore(MethodDeclaration methodNode) {
		ConstructorClassAnalyzer analyzer = new ConstructorClassAnalyzer();
		methodNode.getRoot().accept(analyzer);
		FieldDeclaration[] declarations = analyzer.getFieldDeclarations();
		List<SingleVariableDeclaration> methodParameters = methodNode.parameters();
		compareFieldsAndParameters(methodParameters, declarations);


		return null;
	}

	private static void compareFieldsAndParameters(List<SingleVariableDeclaration> declarations, FieldDeclaration[] fields) {
		List<SimpleName> fieldNames = new ArrayList<SimpleName>();
		for(FieldDeclaration field : fields) {
			List<VariableDeclarationFragment> fragments = field.fragments();
			for(VariableDeclarationFragment fragment : fragments) {
				fieldNames.add(fragment.getName());
			}
		}
		for(SingleVariableDeclaration declaration : declarations) {
			if (fieldNames.contains(declaration.getName())) {
				fieldNames.get(0);
			}
		}
		//1. get List of names from fields name.
		//2. create a list of fields to add the type will be SingleVariable declaration (i can clone the one in the declarations.
		//3.

	}

}
