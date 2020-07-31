

interface FunctionParameter{
  key: string;
  value: string;
  defaultValue?: string;
}

class TypeFunction{
  protected parameters: FunctionParameter[];
  protected functionName: string;
  protected returnType?: string;

  constructor(functionName: string){
    this.functionName = functionName;

  }

}

function test(): any{
}

class ClassFunction extends TypeFunction{
  private accessSpecifier: AccessSpecifier;
  
  constructor(functionName: string){
    super(functionName);
  }
}

function sample(sample:string): Promise<string>{
}

class ClassVariables {

}


enum AccessSpecifier {
  PRIVATE = "PRIVATE",
  PUBLIC = "PUBLIC",
  PROTECTED = "PROTECTED",
  DEFAULT = ""
}

class TypeClass {
  private functions: ClassFunction[] = [];
  private variables: ClassVariables[] = [];
  private className: string;
  private exported: boolean;
  private abstract: boolean;
  private extends: boolean;
  private implements: boolean;


  constructor(className: string, abstract: boolean) {
    this.className = className;
    this.abstract = abstract;
  }


  addFunction(funcName: string, parameters: string, returnType: string, accessSpecifier: AccessSpecifier) {
    this.functions.push();
  }

  addVariable() {

  }


  public get getVariables(): ClassVariables[] {
    return this.variables;
  }

  public get getFunctions(): ClassFunction[] {
    return this.functions;
  }



}