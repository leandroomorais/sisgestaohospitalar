function loadCSS() {
    return `body {
        width: 21cm;
        height: 29.7cm;
        margin: 0;
        padding: 0;
    }

    .content {
        display: flex;
        flex-direction: row;
        align-content: center;
        justify-content: space-between;
        align-items: center;
        padding: 5px;
        margin: 2px;
    }

    .box {
        width: auto;
        height: auto;
        border: 2px solid #686868;
        border-radius: 10px;
        margin-top: 0.5cm;
    }

    .box-title {
        margin-top: 2px;
        display: flex;
        flex-wrap: nowrap;
        justify-content: flex-start;
        flex-direction: column;
        padding: 5px;
    }

    .header {
        font-size: 18pt;
        text-transform: uppercase;
        font-weight: bold;
        text-align: center;
    }

    .title {
        font-size: 14pt;
        text-transform: uppercase;
        font-weight: bold;
        text-align: center;
    }

    .left-text {
        margin-left: 2px;
    }

    .space-simple-top {
        margin-top: 0.5cm;
    }

    .space-simple-bottom {
        margin-bottom: 0.5cm;
    }

    .space-duplo-top {
        margin-top: 1cm;
    }

    .space-duplo-bottom {
        margin-bottom: 1cm;
    }

    .paragraph-justify {
        font-size: 12pt;
        text-align: justify;
    }

    .paragraph-left {
        font-size: 12pt;
        text-align: left;
    }

    .paragraph-center {
        font-size: 12pt;
        text-align: center;
    }`
}