'use strict';
var pdf = null;
let sizeSheet = 0;
let sizeCurrent = 0;
let increment = 0;
let center = null;
const topFile = 3;
const leftFile = 2;
let page = 1;
let headerConf = null;
let footer = null;
let subFooter = null;
let isCreateTable = false;
let isLimit = false;
let incrementSubFooter = 1;
let totalSheetsAtual = 0;
let incrementFooterSubTable = 1;
let footers = [];
let footerInsert = [];
const footerLength = 0.5;
let incrementFooter = footerLength;
let lengthTable = 0;
let incrementDoc = 0.2;

class ArrayExtract {
    // metodo de extrair valores de um array de objetos json
    static extract(obj, object, values){
        const keys = Object.keys(object)
        if (keys.length > 0){
            for (let key of keys){
                if (Object.keys(object[key]).length === 0){
                    values.push(obj[key])
                }
                this.extract(obj[key], object[key], values)
            }
        }
    }
}
/*funcao util para verificar a duplicação no rodapé caso ela exista*/
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

class PDFUtil {
    /*funcao util para transformar os valores em uma matriz*/
    static extractOfArray(objects, object){
        const extract = []
        for (let obj of objects){
            const values = [];
            ArrayExtract.extract(obj, object, values);
            extract.push(values)
        }
        return extract;
    }
    /*funcao util para setar as variaveis globais*/
    static variables(pdf){
        sizeSheet = pdf.internal.pageSize.getHeight() - 5.70;
        center = {left: pdf.internal.pageSize.getWidth() / 2, top: 3};
        headerConf = {left: pdf.internal.pageSize.getWidth() / 2, top: 3};
        footer = {left: headerConf.left, bottom: pdf.internal.pageSize.getHeight() - 2.70};
        subFooter = {left: headerConf.left, bottom: footer.bottom + 2};
    }
    /*funcao util para setar a configuracao do arquivo PDF*/
    static config(orientation, unit, format){
        pdf = new jsPDF(orientation, unit, format);
        this.variables(pdf)
    }
    /*funcao util para ter um controller da "folha" do PDF*/
    static create(){
        sizeCurrent = sizeCurrent + incrementDoc;
        if (this.getPositionLastTable() > sizeCurrent){
            if (this.getPositionLastTable() > sizeSheet){
                this.addPage();
            }
        } else if(sizeCurrent > sizeSheet){
            this.addPage();
        }
    }
    /*funcao util para verificar se foi criado uma tabela*/
    static isCreateTable(){
        if (pdf.autoTable.previous.cursor === undefined){
            return false;
        }
        return true;
    }
    /*funcao util para setar o espaço no arquivo*/
    static createSpace(){
        increment = increment + 1;
        incrementDoc = incrementDoc + 0.2;
        this.create()
    }
    /*funcao util para ter um controlle de posicao da ultima tabela criada*/
    static getPositionLastTable(){
        if (!this.isCreateTable()){
            return topFile + increment;
        }
        if (increment === 0){
            return pdf.autoTable.previous.cursor.y + 0.5;
        }
        if (isLimit){
            isLimit = false;
            return topFile;
        }
        return pdf.autoTable.previous.cursor.y + incrementDoc;
    }
    /*funcao util para inserir um texto na tabela*/
    static createText(text, options){
        const y = this.getPositionLastTable();
        if(options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('12');
            pdf.setFontStyle('normal');
            pdf.text(text, leftFile,
                increment === 0 ? topFile : topFile + increment,
                {
                    align: 'justify',
                }
            );
        }else{
            pdf.setFont(options.fontName);
            pdf.setFontSize(options.fontSize);
            pdf.setFontStyle(options.fontStyle);
            pdf.text(text, options.align === 'center' ? center.left : leftFile,
                increment === 0 ? topFile : isCreateTable ? y - 1 : topFile + increment,
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
    /*funcao util para criar uma tabela com colunas e linhas*/
    static createTableDefault(heades, bodys, option){
        this.create()
        isCreateTable = true;
        const y = this.getPositionLastTable();
        pdf.autoTable({
            margin: option,
            head: [heades],
            body: bodys,
            startY: y,
        });

        if(footers !== undefined && footers != null){
            if(footers.length > 0){
                if(!isNotFooterInsert(footerInsert, this.sizeSheets())){
                    footerInsert.push({pag: this.sizeSheets()})
                    incrementFooter = footerLength;
                    for(let i = 0; i < footers.length; i++){
                        file.insertFooter(footers[i].text);
                    }
                }
            }
        }
    }
    /*funcao util para inserir uma nova pagina no arquivo*/
    static addPage(){
        pdf.addPage();
        isLimit = true;
    }
    /*funcao util para salvar o arquivo .pdf*/
    static save(name){
        pdf.save(name)
        this.clear()
    }
    /*funcao util para restart as variaveis globais*/
    static clear(){
        isLimit = false;
        increment = 0;
        incrementFooter = footerLength;
        page = 1;
        sizeCurrent = 0;
        incrementDoc = 0.2
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
            pdf.text(text, options.align === 'center' ? center.left : leftFile,
                footer.bottom + incrementFooter,
                options.align !== undefined ? options.align : "center"
            );
        }
        incrementFooter = incrementFooter + 0.5;
    }
    /*funcao util para criar o cabecalho do arquivo*/
    static createHeader(text, options){
        if(page > 1){
            this.addPage();
        }
        if (options === undefined){
            pdf.setFont('Times New Roman');
            pdf.setFontSize('16');
            pdf.setFontStyle('bold');
            pdf.text(text, headerConf.left, headerConf.top, 'center');
        }  else {
            CheckOptions.on(options)
            pdf.text(text, headerConf.left, headerConf.top,
                options.align !== undefined ? options.align : 'center');
        }
        this.createSpace();
    }
}
/*class util para verificar se nas funcoes acima estao passando o options*/
class CheckOptions {
    static on(options){
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
}
