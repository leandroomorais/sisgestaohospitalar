
/*class responsavel que contem funcoes para configurar o arquivo .pdf*/
class PDFConfig {
    /*atributo util que contem os formatos disponiveis da lib JsPDF*/
    static format = { // Size in pt of various paper formats
        a0: 'a0',
        a1: 'a1',
        a2: 'a2',
        a3: 'a3',
        a4: 'a4',
        a5: 'a5',
        a6: 'a6',
        a7: 'a7',
        a8: 'a8',
        a9: 'a9',
        a10: 'a10',
        b0: 'b0',
        b1: 'b1',
        b2: 'b2',
        b3: 'b3',
        b4: 'b4',
        b5: 'b5',
        b6: 'b6',
        b7: 'b7',
        b8: 'b8',
        b9: 'b9',
        b10:'b10',
        c0: 'c0',
        c1: 'c1',
        c2: 'c2',
        c3: 'c3',
        c4: 'c4',
        c5: 'c5',
        c6: 'c6',
        c7: 'c7',
        c8: 'c8',
        c9: 'c9',
        c10: 'c10',
        dl: 'dl',
        letter: 'letter',
        government_letter: 'government-letter',
        legal: 'legal',
        junior_legal: 'junior-legal',
        ledger: 'ledger',
        tabloid: 'tabloid',
        credit_card:'credit-card'
    };
    /*atributo util que contem as orientacoes disponiveis da lib JsPDF*/
    static orientation = {
        portrait: "portrait",
        landscape: "landscape",
        p: 'p',
        l: 'l'
    }
    /*atributo util que contem os unit disponiveis da lib JsPDF*/
    static unit = {
        pt: 'pt',
        mm: 'mm',
        cm: 'cm',
        in: 'in',
        px: 'px',
        pc: 'pc',
        em: 'em',
        ex: 'ex'
    }
}

