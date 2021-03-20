'use strict';
var pdf = null;
let sizeSheet = 0;
let sizeCurrent = 0;
let increment = 0;
let position = null;
const topFile = 3;
const topPage = 1;
const leftPageOfTop = 1;
const leftFile = 2;
let page = 1;
let titleConf = null;
let footer = null;
let subFooter = null;
let isCreateTable = false;
let isLimit = false;
let incrementSubFooter = 1;
let incrementFooterSubTable = 1;
let footers = [];
let footerInsert = [];
const footerLength = 0.5;
let incrementFooter = footerLength;
let lengthTable = 0;
let incrementDoc = 0.2;
let sizeTable = 0;
let pagination = 0;
let optionPaginatation = {};
/*funcao util para evitar a duplicação no rodapé*/
function isNotFooterInsert(array, pag){
    if(array.length === 0){
        return false;
    }
    for(let i = 0; i < array.length; i++){
        if(array[i].pag === pag){
            return true;
        }
    }
    return false;
}

function checkPagination() {
    if (optionPaginatation !== undefined && optionPaginatation != null){
        if (optionPaginatation.startPage !== undefined){
            if (optionPaginatation.startPage > 0){
                if (PDFUtil.sizeSheets() === optionPaginatation.startPage){
                    pagination = optionPaginatation.startPage
                }
            } else {
                if (pagination === 0){
                    pagination = 1;
                }
            }
        } else {
            if (pagination === 0){
                pagination = 1
            }
        }
        if (pagination > 0){
            pagination = PDFUtil.sizeSheets();
            PDFUtil.pagination()
        }
    }
}

/*funcao util para setar o footer na pagina no arquivo .pdf*/
function loadFooter() {
    if(footers !== undefined && footers != null){
        if(footers.length > 0){
            if(!isNotFooterInsert(footerInsert, PDFUtil.sizeSheets())){
                footerInsert.push({pag: PDFUtil.sizeSheets()})
                incrementFooter = footerLength;
                for (let ft of footers){
                    PDFUtil.createFooter(ft.text, ft.options);
                }
                checkPagination()
            }
        }
    }


}
/*classe util para manipular os elementos no arquivo pdf*/
class PDFUtil {

    static defaults = {
        topFile: 3,
        leftFile: 2,
        footerLength: 0.5,
        position: {},
    }

    static formTime(date){
        try {
            const dateForm = new Date(date);
            return this.checkNumber(dateForm.getHours()) + ":" + this.checkNumber(dateForm.getMinutes()) + ":";
        }catch (e) {
            throw new DOMException('Informe um objeto data válido');
        }
    }
    static checkNumber(value){
        if (value < 10){
            return "0"+ value;
        }
        return value;
    }
    static formDateTime(date){
        try {
            const dateForm = new Date(date);
            return this.formDate(dateForm) + " | "
                + this.checkNumber(dateForm.getHours()) + ":" + this.checkNumber(dateForm.getMinutes()) + ":"
                + this.checkNumber(dateForm.getSeconds());
        }catch (e) {
            throw new DOMException('Informe um objeto data válido');
        }
    }
    static formDate(date){
        try {
            const dateForm = new Date(date);
            return this.checkNumber(dateForm.getDate())
                + "/" + this.checkNumber(dateForm.getMonth() + 1)
                + "/" + dateForm.getFullYear();
        }catch (e) {
            throw new DOMException('Informe um objeto data válido');
        }
    }
    /*funcao util para transformar os valores em uma matriz*/
    static extractOfArray(objects, object){
        return Extract.extractOfArray(objects, object);
    }
    /*funcao util para transformar os objetos com a chave 'text'*/
    static extractOfArrayToKey(objects, object){
        return Extract.extractOfArrayToKey(objects, object);
    }
    /*funcao util para setar as variaveis globais*/
    static variables(pdf){
        this.defaults.position = {left: this.defaults.leftFile, center: pdf.internal.pageSize.getWidth() / 2, top: this.defaults.topFile, right: pdf.internal.pageSize.getWidth() - 2};
        this.defaults.rightPageOfTop = this.defaults.position.right;
        sizeSheet = pdf.internal.pageSize.getHeight() - 5.70;
        titleConf = {left: this.defaults.position.left, center: this.defaults.position.center, top: this.defaults.position.top, right: this.defaults.position.right};
        footer = {left: this.defaults.position.left, center: this.defaults.position.center,
            bottom: pdf.internal.pageSize.getHeight() - 2.70, right: this.defaults.position.right};
        subFooter = {left: this.defaults.position.left, center: this.defaults.position.center,
            bottom: footer.bottom + 2};
    }
    /*funcao util para setar a configuracao do arquivo PDF*/
    static config(orientation, unit, format, opPaginatation){
        pdf = new jsPDF(orientation, unit, format);
        if (opPaginatation !== undefined && opPaginatation != null){
            if (opPaginatation.isPage){
                optionPaginatation = {
                    ...opPaginatation,
                };
            }
        }
        this.variables(pdf)
    }

    static pagination(){
        if (optionPaginatation.isPage){
            if (optionPaginatation.position === 'top'){
                CheckOptions.on(optionPaginatation.style)
                pdf.text(pagination.toString(), CheckOptions.alignPagination(optionPaginatation), this.defaults.topFile - 1, optionPaginatation.align)
            } else if (optionPaginatation.position === 'bottom') {
                CheckOptions.default()
                pdf.text(pagination.toString(), CheckOptions.alignPagination(optionPaginatation), footer.bottom + 0.5, optionPaginatation.align)
            }
        }
    }
    /*funcao util para retornar a posicao atual do ultimo elemento do arquivo*/
    static get(){
        if (this.getPositionLastTable() > sizeCurrent){
            if (this.getPositionLastTable() > sizeSheet && !isLimit){
                this.addPage()
                return titleConf.top + increment;
            }
        }
        return this.getPositionLastTable();
    }
    /*funcao util para ter um controller da "folha" do PDF*/
    static create(){
        sizeCurrent = sizeCurrent + incrementDoc;
        if (this.getPositionLastTable() > sizeCurrent){
            if (this.getPositionLastTable() > sizeSheet && !isLimit){
                this.addPage();
                sizeCurrent = 0;
            }
        } else if(sizeCurrent > sizeSheet){
            this.addPage();
            sizeCurrent = 0;
        }
    }
    /*funcao util para verificar se foi criado uma tabela*/
    static isCreateTable(){
        return pdf.autoTable.previous.cursor !== undefined;
    }
    /*funcao util para setar o espaço no arquivo*/
    static createSpace(){
        increment = increment + 1;
        incrementDoc = incrementDoc + 0.2;
        this.create();
    }
    /*funcao util para remover o espaço no arquivo*/
    static removeSpace(){
        increment = increment - (3);
        incrementDoc = incrementDoc - (0.2 * 3);
    }
    /*funcao util para ter um controlle de posicao da ultima tabela criada*/
    static getPositionLastTable(){
        if (!this.isCreateTable()){
            return titleConf.top + increment;
        }
        if (increment === 0){
            return pdf.autoTable.previous.cursor.y + 0.5;
        }

        if (isLimit){
            return titleConf.top;
        }

        return (pdf.autoTable.previous.cursor.y) + 1.79999999;
    }
    /*funcao util para inserir um texto na tabela*/
    static createText(text, options){
        let y = this.get();

        if(options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('12');
            pdf.setFontStyle('normal');
            pdf.text(text, leftFile,
                increment === 0 ? titleConf.top : isCreateTable ? y - 1: titleConf.top + increment,
                {
                    align: 'justify',
                }
            );
        }else{
            CheckOptions.on(options)
            pdf.text(text, CheckOptions.alignText(options),
                increment === 0 ? titleConf.top : isCreateTable ? y - 1: titleConf.top + increment,
                options.align
            );
        }

        if (!isCreateTable){
            this.createSpace();
        }
        isCreateTable = false;
    }
    /*funcao util para retornar o tamanho de paginas no arquivo*/
    static sizeSheets(){
        return pdf.autoTable.previous.startPageNumber
    }
    /*funcao util para criar uma legenda*/
    static createLegends(text, options){
        let y = this.get()
        if(options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('12');
            pdf.setFontStyle('normal');
            pdf.text(text, leftFile,
                increment === 0 ? topFile : isCreateTable ? y - 0.2  : topFile + increment,
                {
                    align: 'justify',
                }
            );
        }else{
            /*if (CheckPosition.isTop(y)){
                y = y + 0.7
            } else {
                y = y - 0.2
            }*/
            CheckOptions.on(options)
            if (options.align !== undefined){

                pdf.text(text, CheckOptions.alignText(options),
                    increment === 0 ? topFile : isCreateTable ? y - 0.2: topFile + increment,
                    options.align
                );
            } else {
                pdf.text(text, leftFile,
                    increment === 0 ? topFile : isCreateTable ? y - 0.2 : topFile + increment,
                    {
                        align: 'justify',
                    }
                );
            }

        }
    }
    /*funcao util para criar uma tabela com colunas*/
    static createTable(heades, bodys, title, option){
        let autoIncrement = 1;

        this.create()
        for (let body of bodys){
            let y = this.get();
            isCreateTable = true;
            let lines = [];
            let i = 0;

            if (title !== undefined){
                const text = title.options.auto !== undefined ? title.options.auto ? autoIncrement : '' : '';
                this.createLegends(title.text + ' ' + text, title.options)
                autoIncrement++;
            }

            while(i < heades.length){
                if(heades[++i] != null){
                    i--;
                    const array = [heades[i].text, body[i].text, heades[++i].text, body[i].text];

                    lines.push(array);
                }
                i++;
            }

            pdf.autoTable({
                columnStyles: { 0: {fontStyle: 'bold'}, 2: {fontStyle: 'bold'} }, // Cells in first column centered and green
                margin: option,
                body: lines,
                startY: y,
            });

            loadFooter()
        }
    }
    /*funcao util para criar uma tabela com colunas e linhas*/
    static createTableDefault(heades, bodys, option){
        this.create()
        isCreateTable = true;
        const y = CheckPosition.set(this.get(), 1);
        pdf.autoTable({
            margin: option,
            head: [heades],
            body: bodys,
            startY: y,
        });

        loadFooter()
    }
    /*funcao util para inserir uma nova pagina no arquivo*/
    static addPage(){
        isLimit = true;
        sizeSheet = 0;
        sizeCurrent = 0;
        increment = 0;
        position = null;
        page = 1;
        titleConf = null;
        footer = null;
        subFooter = null;
        isCreateTable = false;
        isLimit = false;
        lengthTable = 0;
        incrementDoc = 0.2;
        if (pdf.autoTable.previous.cursor !== undefined){
            pdf.autoTable.previous.cursor = undefined;
        }
        this.variables(pdf)
        loadFooter()
        pdf.addPage();
    }
    /*funcao util para salvar o arquivo .pdf*/
    static save(name){
        pdf.save(name)
        this.clear()
    }
    /*funcao util para restart as variaveis globais*/
    static clear(){
        sizeSheet = 0;
        sizeCurrent = 0;
        increment = 0;
        position = null;
        page = 1;
        titleConf = null;
        footer = null;
        subFooter = null;
        isCreateTable = false;
        isLimit = false;
        incrementSubFooter = 1;
        incrementFooterSubTable = 1;
        footers = [];
        footerInsert = [];
        incrementFooter = footerLength;
        lengthTable = 0;
        incrementDoc = 0.2;
        pdf.autoTable.previous.cursor = undefined;
    }
    /*funcao util para inserir o rodapé no arquivo*/
    static createFooter(text, options){
        if (options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('10');
            pdf.setFontStyle('bold');
            pdf.text(text, footer.left,  footer.bottom + incrementFooter, "center");
        } else {
            CheckOptions.on(options)
            pdf.text(text, CheckOptions.alignFooter(options),
                footer.bottom + incrementFooter,
                options.align !== undefined ? options.align : "center"
            );
        }
        incrementFooter = incrementFooter + 0.5;
    }
    /*funcao util para criar o cabecalho do arquivo*/
    static createTitle(text, options){
        if(page > 1){
            this.addPage();
        }
        if (options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('16');
            pdf.setFontStyle('bold');
            pdf.text(text, titleConf.left, titleConf.top, 'center');
        }  else {
            CheckOptions.on(options)
            pdf.text(text, CheckOptions.alignTitle(options), titleConf.top,
                options.align !== undefined ? options.align : 'center');
        }
        page++;
        this.createSpace();
    }
    /*funcao util para criar uma linha vertical*/
    static lineVertical(lW, pWidth, pStart, pEnd){
       this.lineWidth(lW)
       const ver = Line.vertical(pWidth, pStart, pEnd);
       pdf.line(ver.x1, ver.y1, ver.x2, ver.y2);
    }
    /*funcao util para criar uma linha horizontal*/
    static lineHorizontal(lW){
        this.lineWidth(lW)
        const hor = Line.horizontal(3);
        pdf.line(hor.x1, hor.y1, hor.x2, hor.y2);
    }
    /*funcao util para setar a largura da linha*/
    static lineWidth(lW){
        pdf.setLineWidth(lW);
    }
    /*funcao util para title e linha*/
    static createTitleAndLine(text, options, type){
        if (type === 'horizontal'){
            this.createTitle(text, options)
            this.lineHorizontal(0.1)
        } else if (type === 'vertical'){
            this.createTitle(text, options)
            this.lineVertical(0.02, position.right, topFile * 2, sizeCurrent * topFile)
        }
    }
    /*funcao util para criar rodapé nas paginas*/
    static createFooterAutoPage(fs){
        footers = fs;
    }
}

class CheckPosition{
    static isTop(y){
        if (y === topFile){
            if (isLimit){
                isLimit = false;
            }
            return true;
        }
        return false;
    }

    static setOfDefault(y, defaultP){
        if (this.isTop(y) && defaultP !== undefined){
            return y + defaultP;
        }
        return y - defaultP;
    }

    static set(y, update){
        if (this.isTop(y) && update !== undefined){
            return y + update;
        }
        return y;
    }
}

/*class util para verificar se nas funcoes acima estao passando o options*/
class CheckOptions {
    static alignTitle(options){
        if (options.align !== undefined){
            switch (options.align.toUpperCase()){
                case 'right'.toUpperCase():
                    return titleConf.right;
                case 'left'.toUpperCase():
                    return titleConf.left;
                case 'center'.toUpperCase():
                    return titleConf.center;
                default:
                    return titleConf.left;
            }
        }
    }

    static alignText(options){
        if (options.align !== undefined){
            switch (options.align.toUpperCase()){
                case 'right'.toUpperCase():
                    return PDFUtil.defaults.position.right;
                case 'left'.toUpperCase():
                    return PDFUtil.defaults.position.left;
                case 'center'.toUpperCase():
                    return PDFUtil.defaults.position.center;
                default:
                    return PDFUtil.defaults.position.left;
            }
        }
    }

    static alignPagination(options){
        if (options.align !== undefined){
            switch (options.align.toUpperCase()){
                case 'right'.toUpperCase():
                    return PDFUtil.defaults.position.right;
                case 'left'.toUpperCase():
                    return PDFUtil.defaults.position.left - 0.5;
                case 'center'.toUpperCase():
                    return PDFUtil.defaults.position.center;
                default:
                    return PDFUtil.defaults.position.left - 0.5;
            }
        }
    }

    static alignFooter(options){
        if (options.align !== undefined){
            switch (options.align.toUpperCase()){
                case 'right'.toUpperCase():
                    return footer.right;
                case 'left'.toUpperCase():
                    return footer.left;
                default:
                    return footer.left;
            }
        }
    }

    static default(){
        pdf.setFont('Times New Roman');
        pdf.setFontSize('10');
        pdf.setFontStyle('normal');
    }

    static on(options){
        if (options !== undefined){
            if (options.fontName !== undefined){
                pdf.setFont(options.fontName)
            } else {
                pdf.setFont('Times New Roman');
            }

            if (options.fontSize !== undefined){
                pdf.setFontSize(options.fontSize)
            } else {
                pdf.setFontSize('12');
            }

            if (options.fontStyle !== undefined){
                pdf.setFontStyle(options.fontStyle)
            } else {
                pdf.setFontStyle('normal');
            }
        }
        else {
            this.default()
        }
    }
}
