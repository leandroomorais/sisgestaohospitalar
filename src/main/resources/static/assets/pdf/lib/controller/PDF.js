/*class responsavel para utilizar outras funcoes internas e externas a ela*/

class PDF {

    static config(orientation, unit, format){
        PDFUtil.config(orientation, unit, format);
    }

    static createText(text, options){
        PDFUtil.createText(text, options)
    }

    static save(name){
        PDFUtil.save(name)
    }

    static createSpace(){
        PDFUtil.createSpace();
    }

    static createHeader(text){
        PDFUtil.createHeader(text);
    }

    static createTableDefault(heades, bodys, option){
        PDFUtil.createTableDefault(heades, bodys, option);
    }

    static createFooter(text, options) {
        PDFUtil.createFooter(text, options);
    }
}
