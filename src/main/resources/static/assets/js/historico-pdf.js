
function checkNotUnderfined(obj) {
	return obj?.data !== undefined
}

function checkNotNull(obj) {
	return obj !== null
}

function title(value) {
	return `<div class="text-left">
				<h4 class="strong">${value}</h4>
			</div>`
}

$("#historico-pdf").click(function () {
	const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
	const templateDoc = new TemplateDoc(doc)
	const elementsCount = 1;
	templateDoc.getHtml({
		title: "Documento gerado pelo SGH", links: [
			{ type: 'text/css', href: 'http://localhost:9090/assets/css/bootstrap.min.css' },
			{ type: 'text/css', href: 'http://localhost:9090/assets/css/atlantis.css' },
		]
	})
	templateDoc.createStyle({
		value: `@media print {
					.card-footer {
						display: none;
					}
        		}
				.strong {
					text-transform: uppercase;
				}
				`})
	templateDoc.getBody({class: 'card'})
	templateDoc.createHTML({
		innerHTML: `<div class="card-header text-center">
						<p>MINISTÉRIO DA SAÚDE<br> ESTADO DE RIO GRANDE DO NORTE<br> MUNICÍPIO DE SEVERIANO MELO<br> UNIDADE DE
							SAÚDE Hospital Maternidade Municipal de Severiano Melo</p>
					</div>
					<div class="card-body">
						<div class="text-center">
							<h2 class="strong">Histórico da Atenção Básica - ESUS</h2>
						</div>
						<span id="children">
						</span>
					</div>
					`})
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

	if (checkNotNull(medicamentoPres)) {
		templateDoc.createHTML({parentElement: {id: 'children'}, innerHTML: `${title("medicamentos prescritos")}`})

		const values = ExtractPDF.extractOfElements(medicamentoLabels, medicamentoPres, 1)
		templateDoc.createElements({
			parentElement: {id: 'children'},
			values: values, onRender: (values, index) => `<div class="card">
			<div class="card-header">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Medicamento </p>
			</div>
		    <div class="row">
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="card-body">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span>
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
			</div>		
		</div>`})
	}

	if (checkNotNull(examesS)) {
		templateDoc.createHTML({ innerHTML: `${title("EXAMES SOLICITADOS")}`,
								parentElement: {id: 'children'}})
		const values = ExtractPDF.extractOfElements(examesSLabels, examesS, elementsCount)
		templateDoc.createElements({
			parentElement: {id: 'children'},
			values: values, onRender: (values, index) => `<div class="card">
			<div class="card-header">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Exame </p>
			</div>
			<div class="row">
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="card-body">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span>
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
			</div>
		</div>`})
	}

	if (checkNotNull(examesR)) {
		templateDoc.createHTML({  innerHTML: `${title("RESULTADOS DE EXAMES")}`, parentElement: {id: 'children'},})
		const values = ExtractPDF.extractOfElements(examesRLabels, examesR, elementsCount)
		templateDoc.createElements({
			parentElement: {id: 'children'},
			values: values, onRender: (values, index) => `<div class="card">
			<div class="card-header">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Exame </p>
			</div>
				<div class="row">
					${ExtractPDF.extractMap(values.cols, (tCol, index) =>
						`<div class="card-body">
							${ExtractPDF.extractMap(tCol.elements, (value, index) =>
								`<span>
									<b>${value.label}: </b>
									<p class="left-text">${value.value}</p>
								</span>`
							)}
						</div>`
					)}
				</div>
		</div>`})
	}

	if (checkNotNull(vacinasApp)) {

		templateDoc.createHTML({parentElement: {id: 'children'}, innerHTML: `${title("Vacinas Aplicadas")}`})

		const values = ExtractPDF.extractOfElements(vacinasAppLabels, vacinasApp, elementsCount)
		templateDoc.createElements({
			parentElement: {id: 'children'},
			values: values, onRender: (values, index) => `<div class="card">
			<div class="card-header">
				<p style="
		margin: 0;
		font-weight: bold;
		">${index + 1}. Vacina </p>
			</div>
			<div class="row">
				${ExtractPDF.extractMap(values.cols, (tCol, index) =>
					`<div class="card-body">
						${ExtractPDF.extractMap(tCol.elements, (value, index) =>
							`<span>
								<b>${value.label}: </b>
								<p class="left-text">${value.value}</p>
							</span>`
						)}
					</div>`
				)}
			</div>
		</div>`})
	}

	if (checkNotNull(vacinasAgen)) {
		templateDoc.createHTML({parentElement: {id: 'children'}, innerHTML: `${title("Vacinas agentadas")}`})

		const values = ExtractPDF.extractOfElements(vacinasAppLabels, vacinasApp, elementsCount)
		templateDoc.createElements({
			parentElement: {id: 'children'},
			values: values, onRender: (values, index) => `<div class="card">
			<div class="card-header">
				<p style="
					margin: 0;
					font-weight: bold;
					">${index + 1}. Vacina 
				</p>
			</div>
				<div class="row">
					${ExtractPDF.extractMap(values.cols, (tCol, index) =>
						`<div class="card-body">
								${ExtractPDF.extractMap(tCol.elements, (value, index) =>
									`<span>
										<b>${value.label}: </b>
										<p class="left-text">${value.value}</p>
									</span>`
								)}
						</div>`
					)}
				</div>
		</div>`})
	}
	templateDoc.createHTML({parentElement: {id: 'children'}, innerHTML: `
	<div class="text-center">
		<p>Data: 20/11/2021</p>
	</div>
	<br/>
	<div class="text-center">
		<p>ZIRALDO GOMES HOLANDA MELO</p>
		<span>CRM: 8392 / RN</span>
	</div>
	`})
	templateDoc.createHTML({innerHTML: `<div class="card-footer">
			<button onclick="window.print()" class="btn btn-primary">Imprimir</button>
	</div>`})
});
