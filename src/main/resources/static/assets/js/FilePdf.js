'use strict';

var file = {};

var pdf = new jsPDF('p', 'cm', 'a4');
let incremet = 0;
const center = {left: pdf.internal.pageSize.getWidth() / 2, top: 3};
const topFile = 3;
const leftFile = 2;
let page = 1;
let headerConf = {left: pdf.internal.pageSize.getWidth() / 2, top: 3};
let footer = {left: headerConf.left, bottom: pdf.internal.pageSize.getHeight() - 2.70};
let subFooter = {left: headerConf.left, bottom: footer.bottom + 2}
let isCreateTable = false;

let incrementSubFooter = 1;
let totalSheetsAtual = 0;
let incrementFooterSubTable = 1;
let footers = [];
let footerInsert = [];

let incrementFooter = 0.5;

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

file.space = function (){
    ++incremet;
}

file.insertHeader = function (text){
    if(page > 1){
        pdf.addPage();
        incremet = 0;
        incrementFooter = 1;
    }
    pdf.setFont('Times New Roman');
    pdf.setFontSize('16');
    pdf.setFontStyle('bold');
    pdf.text(text, headerConf.left, headerConf.top, 'center');
    incremet += 1;
    page++;
}

file.insertFooter = function(text, options){
    if (options === undefined){
        pdf.setFont('Times New Roman');
        pdf.setFontSize('10');
        pdf.setFontStyle('bold');
        pdf.text(text, footer.left,  footer.bottom + incrementFooter, "center");
    } else {
        pdf.setFont(options.fontName);
        pdf.setFontSize(options.fontSize);
        pdf.setFontStyle(options.fontStyle);
        pdf.text(text, options.align === 'center' ? center.left : leftFile,
            footer.bottom + incrementFooter,
            options.align
        );
    }
    incrementFooter = incrementFooter + 0.5;
}


file.insertText = function (text, options){
    const y = pdf.autoTable.previous.cursor !== undefined && isCreateTable ? pdf.autoTable.previous.cursor.y : 0;
    if(options === undefined){
        pdf.setFont('Times New Roman');
        pdf.setFontSize('12');
        pdf.setFontStyle('normal');
        pdf.text(text, leftFile,
            incremet === 0 ? topFile : topFile + incremet,
            {
                align: 'justify',
            }
        );
    }else{
        pdf.setFont(options.fontName);
        pdf.setFontSize(options.fontSize);
        pdf.setFontStyle(options.fontStyle);
        pdf.text(text, options.align === 'center' ? center.left : leftFile,
            incremet === 0 ? topFile : isCreateTable ? y + (topFile - 2) : topFile + incremet,
            options.align
        );
    }
    isCreateTable = false;
    ++incremet;
}

file.totalSheets = function(){
    return pdf.autoTable.previous.startPageNumber;
}


file.insertTableDefault = function (heades, bodys, option){
    isCreateTable = true;
    pdf.autoTable({
        margin: option,
        head: [heades],
        body: bodys,
        startY: topFile + incremet,
    });

    if(footers !== undefined && footers != null){
        if(footers.length > 0){
            if(!isNotFooterInsert(footerInsert, file.totalSheets())){
                footerInsert.push({pag: file.totalSheets()})
                incrementFooter = 1;
                for(let i = 0; i < footers.length; i++){
                    file.insertFooter(footers[i].text);
                }
            }
        }
    }
}

file.insertImage = function (img, format, x, y, w, h, alias, compression, rotation){
    pdf.addPage();
    pdf.addImage(img, format, x, y, w, h, alias, compression, rotation);
}

file.insertImageHeader = function (text, img, format, x, y, w, h, alias, compression, rotation){
    incrementSubFooter = 1;
    incrementFooter = 1;
    pdf.addPage();
    file.insertHeader(text);
    pdf.addImage(img, format, x < 0 && x < 20 ? 20 : x, y < 0 && y < 120 ? 120 : y, w, h, alias, compression, rotation);
}

file.insertSubTable = function(heades, bodys, option){
    isCreateTable = true;
    let lines = [];
    let i = 0;
    while(i < heades.length){
        if(heades[++i] != null){
            i--;
            const array = [heades[i].text, bodys[i].text, heades[++i].text, bodys[i].text];

            lines.push(array);
        }
        i++;
    }

    pdf.autoTable({
        columnStyles: { 0: {fontStyle: 'bold'}, 2: {fontStyle: 'bold'} },
        margin: option,
        body: lines,
    });

    if(footers !== undefined && footers != null){
        if(footers.length > 0){
            if(!isNotFooterInsert(footerInsert, file.totalSheets())){
                footerInsert.push({pag: file.totalSheets()})
                incrementFooter = 1;
                for(let i = 0; i < footers.length; i++){
                    file.insertFooter(footers[i].text);
                }
            }
        }
    }
}

file.insertTable = function(heades, bodys, descrition){
    file.insertHeader(descrition);
    isCreateTable = true;
    let lines = [];
    let i = 0;
    while(i < heades.length){
        if(heades[++i] != null){
            i--;
            const array = [heades[i].text, bodys[i].text, heades[++i].text, bodys[i].text];

            lines.push(array);
        }
        i++;
    }

    pdf.autoTable({
        columnStyles: { 0: {fontStyle: 'bold'}, 2: {fontStyle: 'bold'} }, // Cells in first column centered and green
        margin: { top: 100 },
        body: lines,
    });

    if(footers !== undefined && footers != null){
        if(footers.length > 0){
            if(!isNotFooterInsert(footerInsert, file.totalSheets())){
                footerInsert.push({pag: file.totalSheets()})
                incrementFooter = 1;
                for(let i = 0; i < footers.length; i++){
                    file.insertFooter(footers[i].text);
                }
            }
        }
    }
}

file.insertPage = function (){
    pdf.addPage();
    incremet = 0;
    incrementFooter = 1;
    incrementSubFooter = 1;
    incrementFooterSubTable = 1;
}

file.save = function (name){
    pdf.save(name);
    incremet = 0;
    page = 0;
    incrementFooter = 1;
    incrementSubFooter = 1;
    incrementFooterSubTable = 1;
}
