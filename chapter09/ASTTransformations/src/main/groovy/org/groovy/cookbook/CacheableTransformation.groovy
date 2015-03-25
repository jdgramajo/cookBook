package org.groovy.cookbook

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstructorCallExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import java.lang.reflect.Modifier

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class CacheableTransformation implements ASTTransformation {
  
  @Override
  void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
    
    if (doTransform(astNodes)) {
      
      MethodNode annotatedMethod = astNodes[1]
      BlockStatement methodCode = annotatedMethod.code
      
      def methodStatements = annotatedMethod.code.statements
      def parameterName = annotatedMethod.parameters[0].name
      def cachedFieldName = annotatedMethod.name
      def declaringClass = annotatedMethod.declaringClass
      
      FieldNode cachedField = new FieldNode("cache_${cachedFieldName}", Modifier.PRIVATE,
        new ClassNode(Map), new ClassNode(declaringClass.getClass()),
        new ConstructorCallExpression(new ClassNode(HashMap), new ArgumentListExpression()))
      
      declaringClass.addField(cachedField)
      
      Statement oldReturnStatement = methodCode.statements.last()
      
      def ex = oldReturnStatement.expression
      def stats = """
        def cached = cache_${cachedFieldName}.
        get(${parameterName})
        if (cached) {
          return cached
        }
      """

      List<ASTNode> checkMap = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS,
        true, stats)

      def putInMap = new AstBuilder().buildFromSpec {
        expression {
          declaration {
            variable "localCalculated_${cachedFieldName}"
            token '='
            { -> delegate.expression << ex }()
          }
        }
        expression {
          methodCall {
            variable "cache_$cachedFieldName"
            constant 'put'
            argumentList {
              variable parameterName
              variable "localCalculated_${cachedFieldName}"
            }
          }
        }
        returnStatement {
          variable "localCalculated_${cachedFieldName}"
        }
      }

      methodStatements.remove(oldReturnStatement)
      methodStatements.add(0, checkMap[0])
      methodStatements.add(putInMap[0])
      methodStatements.add(putInMap[1])
      methodStatements.add(putInMap[2])
    }

  }
  
  boolean doTransform(ASTNode[] astNodes) {
    astNodes && astNodes[0] && astNodes[1] &&
    (astNodes[0] instanceof AnnotationNode) &&
    (astNodes[1] instanceof MethodNode) &&
    (astNodes[1].parameters.length == 1) &&
    (astNodes[1].returnType.name == 'void')
  } 

}