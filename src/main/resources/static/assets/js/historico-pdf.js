
function checkNotUnderfined(obj) {
	return obj?.data !== undefined
}

function checkNotNull(obj) {
	return obj !== null
}



$("#historico-pdf").click(function () {
	console.log(profissional)
	const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
    const document = doc.getDoc()
    const templateDoc = new TemplateDoc(document)
    templateDoc.openHtml({ label: "Documento gerado pelo SGH" })
    templateDoc.createStyle({ value: loadCSS() })
    templateDoc.openBody()
	templateDoc.createHeader({
        value: `MINISTÉRIO DA SAÚDE
    ESTADO DE RIO GRANDE DO NORTE
    MUNICÍPIO DE SEVERIANO MELO
    UNIDADE DE SAÚDE Hospital Maternidade Municipal de Severiano Melo`})
	let examesS = null
	let examesSLabels = null

	if (checkNotUnderfined(examesSolicitations)) {

		examesSLabels = [
			'Procedimento',
			'Procedimento de Forma Organizacional',
			'Procedimento Sub Grupo',
		]

		examesS = ExtractPDF.extractOfArrayNotKey(examesSolicitations.data, {
			procedimento: {
				nomeProcedimento: '',
				procedimentoFormaOrganizacional: {
					noProcedFormaOrganizacional: ''
				},
				procedimentoSubGrupo: {
					noProcedSubGrupo: ''
				},
			}
		});
	}

	let examesR = null
	let examesRLabels = null

	if (checkNotUnderfined(examesResult)) {
		examesRLabels = [
			'Procedimento',
			'Procedimento de Forma Organizacional',
			'Procedimento Sub Grupo',
			'Data Solicitação',
			'Data Resultado',
			'Resultado'
		]
		examesResult = ExtractPDF.extractOfArrayNotKey(examesResult.data, {
			procedimento: {
				nomeProcedimento: '',
				procedimentoFormaOrganizacional: {
					noProcedFormaOrganizacional: ''
				},
				procedimentoSubGrupo: {
					noProcedSubGrupo: ''
				}
			},
			dataSolicitacao: '',
			dataResultado: '',
			resultado: ''
		});
	}

	let medicamentoPres = null
	let medicamentoLabels = null

	if (checkNotUnderfined(receitas)) {
		medicamentoLabels = [
			'Principio Ativo',
			'Concentração',
			'Forma',
			'Posologia',
			'Recomendação'
		]
		medicamentoPres = ExtractPDF.extractOfArrayNotKey(receitas.data, {
			medicamento: {
				principio_ativo: '',
				concentracao: '',
				formaFarmaceutica: {
					nomeFormaFarmaceutica: '',
				}
			},
			posologia: '',
			recomendacao: ''
		})
	}

	let vacinasApp = null
	let vacinasAppLabels = null

	if (checkNotUnderfined(vacinasAplicadas)) {
		vacinasAppLabels = [
			'Observação',
			'Data Aplicação',
			'Vacina',
			'Codigo Dose'
		]
		vacinasApp = ExtractPDF.extractOfArrayNotKey(vacinasAplicadas.data, {
			observacao: '',
			dataAplicacao: '',
			codigoImunobiologico: {
				nomeViadministracaoVacina: '',
				//nomeImunobiologico: '',
				//codigoClasseImunobiologico: ''
			},
			codigoDoseImunobiologico: {
				//sgDoseImunobiologico: '',
				nomeDoseImunobiologico: '',
				//numeroOrdem: ''
			}
		})
	}

	let vacinasAgen = null
	let vacinasAgenLabels = null
	if (checkNotUnderfined(vacinasAgendadas)) {
		vacinasAgenLabels = [
			'Observação',
			'Data Aprazamento',
			'Vacina',
			'Codigo Dose'
		]
		vacinasAgen = PDFUtil.extractOfArrayToKey(vacinasAgendadas.data, {
			observacao: '',
			dataAprazamento: '',
			codigoImunobiologico: {
				nomeViadministracaoVacina: '',
				//nomeImunobiologico: '',
				//codigoClasseImunobiologico: ''
			},
			codigoDoseImunobiologico: {
				//sgDoseImunobiologico: '',
				nomeDoseImunobiologico: '',
				//numeroOrdem: ''
			}
		})
	}

	if (checkNotNull(examesS)) {
		console.log(examesS, examesSLabels)
		templateDoc.createText({ value: `EXAMES SOLICITADOS`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-top' } })
		const values = ExtractPDF.extractOfElements(examesSLabels, examesS, 2)
		templateDoc.createElements({
			values: values, onRender: (values, index) => `<div class="box">
			<div class="box-title">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Exame </p>
				<hr width="100%" color="#686868" size="0.5px">
			</div>
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="content">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span style="margin-right: 10px
							;">
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
		</div>`})
	}

	if (checkNotNull(examesR)) { 
		templateDoc.createText({ value: `RESULTADOS DE EXAMES`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-top' } })
		const values = ExtractPDF.extractOfElements(examesRLabels, examesR, 2)
		templateDoc.createElements({
			values: values, onRender: (values, index) => `<div class="box">
			<div class="box-title">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Exame </p>
				<hr width="100%" color="#686868" size="0.5px">
			</div>
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="content">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span style="margin-right: 10px
							;">
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
		</div>`})
	}

	if (checkNotNull(medicamentoPres)) {
		templateDoc.createText({ value: `medicamentos prescritos`.toUpperCase(), attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-top' } })

		const values = ExtractPDF.extractOfElements(medicamentoLabels, medicamentoPres, 2)
		templateDoc.createElements({
			values: values, onRender: (values, index) => `<div class="box">
			<div class="box-title">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Medicamento </p>
				<hr width="100%" color="#686868" size="0.5px">
			</div>
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="content">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span style="margin-right: 10px
							;">
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
		</div>`})
	}

	if (checkNotNull(vacinasApp)) {

		templateDoc.createText({ value: `Vacinas Aplicadas`.toUpperCase(), attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-top' } })

		const values = ExtractPDF.extractOfElements(vacinasAppLabels, vacinasApp, 2)
		templateDoc.createElements({
			values: values, onRender: (values, index) => `<div class="box">
			<div class="box-title">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Vacina </p>
				<hr width="100%" color="#686868" size="0.5px">
			</div>
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="content">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span style="margin-right: 10px
							;">
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
		</div>`})
	}

	if (checkNotNull(vacinasAgen)) {
		templateDoc.createText({ value: `Vacinas agentadas`.toUpperCase(), attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-top' } })

		const values = ExtractPDF.extractOfElements(vacinasAppLabels, vacinasApp, 2)
		templateDoc.createElements({
			values: values, onRender: (values, index) => `<div class="box">
			<div class="box-title">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Vacina </p>
				<hr width="100%" color="#686868" size="0.5px">
			</div>
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="content">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span style="margin-right: 10px
							;">
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
		</div>`})
	}
    templateDoc.createText({ value: `Solicitante`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-center' }, attrsFatherParent: { class: 'space-simple-bottom' } })
    templateDoc.closeBody()
    templateDoc.closeHtml()
    doc.print()

});
