{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "xsk.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "xsk.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "xsk.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "xsk.labels" -}}
helm.sh/chart: {{ include "xsk.chart" . }}
{{ include "xsk.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/*
Selector labels
*/}}
{{- define "xsk.selectorLabels" -}}
app.kubernetes.io/name: {{ include "xsk.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/*
Create the name of the service account to use
*/}}
{{- define "xsk.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
    {{ default (include "xsk.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
    {{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}

{{/*
Create the name of the role collections to use
*/}}
{{- define "xsk.roleCollections" -}}
- description: XSK Developer
  name: XSK-{{ default .Release.Name .Values.kyma.roleCollectionsNameDeveloper }}-Developer
  role-template-references:
    - $XSAPPNAME.Developer
- description: XSK Operator
  name: XSK-{{ default .Release.Name .Values.kyma.roleCollectionsNameOperator }}-Operator
  role-template-references:
    - $XSAPPNAME.Operator
{{- end -}}

{{/*
Create the name of the role templates to use
*/}}
{{- define "xsk.roleTemplates" -}}
- description: Developer related roles
  name: "Developer"
  scopeReferences:
    - $XSAPPNAME.Developer
- description: Operator related roles
  name: "Operator"
  scopeReferences:
    - $XSAPPNAME.Operator
{{- end -}}

{{/*
Create the name of the scopes to use
*/}}
{{- define "xsk.scopes" -}}
- description: Developer scope
  name: $XSAPPNAME.Developer
- description: Operator scope
  name: $XSAPPNAME.Operator
{{- end -}}

{{/*
Create secret to pull the private image
*/}}
{{- define "imagePullSecret" }}
{{- with .Values.application }}
{{- printf "{\"auths\":{\"%s\":{\"dockerUsername\":\"%s\",\"dockerPassword\":\"%s\",\"dockerEmail\":\"%s\",\"auth\":\"%s\"}}}" .dockerServer .dockerUsername .dockerPassword .dockerEmail (printf "%s:%s" .dockerUsername .dockerPassword | b64enc) | b64enc }}
{{- end }}
{{- end }}
