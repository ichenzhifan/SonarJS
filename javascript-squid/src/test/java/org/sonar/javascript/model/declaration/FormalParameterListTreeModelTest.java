/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011 SonarSource and Eriks Nukis
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.javascript.model.declaration;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;
import org.sonar.javascript.api.EcmaScriptPunctuator;
import org.sonar.javascript.model.JavaScriptTreeModelTest;
import org.sonar.javascript.model.interfaces.Tree.Kind;
import org.sonar.javascript.model.interfaces.declaration.FormalParameterListTree;

public class FormalParameterListTreeModelTest extends JavaScriptTreeModelTest {


  @Test
  public void parameters() throws Exception {
    FormalParameterListTree tree = parse("function f(p1, p2, ...p3) {};", Kind.FORMAL_PARAMETER_LIST);

    assertThat(tree.is(Kind.FORMAL_PARAMETER_LIST)).isTrue();
    assertThat(tree.openParenthesis().text()).isEqualTo(EcmaScriptPunctuator.LPARENTHESIS.getValue());
    assertThat(tree.formalParameters().getSeparators().size()).isEqualTo(2);
    assertThat(tree.closeParenthesis().text()).isEqualTo(EcmaScriptPunctuator.RPARENTHESIS.getValue());
  }


  @Test
  public void no_parameter() throws Exception {
    FormalParameterListTree tree = parse("function f() {};", Kind.FORMAL_PARAMETER_LIST);

    assertThat(tree.is(Kind.FORMAL_PARAMETER_LIST)).isTrue();
    assertThat(tree.openParenthesis().text()).isEqualTo(EcmaScriptPunctuator.LPARENTHESIS.getValue());
    assertThat(tree.formalParameters().getSeparators().size()).isEqualTo(0);
    assertThat(tree.closeParenthesis().text()).isEqualTo(EcmaScriptPunctuator.RPARENTHESIS.getValue());
  }

}