Set objExcel = CreateObject("Excel.Application")
objExcel.Application.Run "'D:\ICICI_Lombard_Seva_Project\Exec\ICICI_Policy_Start\tools\src\OTC_Execute.xls'!Module2.OTC_ExecuteCalculator"
objExcel.DisplayAlerts = False
objExcel.Application.Quit
Set objExcel = Nothing