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

    static createTitle(text, options){
        PDFUtil.createTitle(text, options);
    }

    static createLineVertical(lw){
        PDFUtil.lineVertical(lw);
    }

    static createTitleAndLine(text, options, type){
        PDFUtil.createTitleAndLine(text, options, type)
    }

    static createLineHorizontal(lw){
        PDFUtil.lineHorizontal(lw);
    }

    static createTableDefault(heades, bodys, option){
        PDFUtil.createTableDefault(heades, bodys, option);
    }

    static createTable(heades, bodys, title, option){
        PDFUtil.createTable(heades, bodys, title, option);
    }

    static createFooter(text, options) {
        PDFUtil.createFooter(text, options);
    }
}
