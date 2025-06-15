    package com.example.mapservice;

    import javax.jws.WebMethod;
    import javax.jws.WebParam;
    import javax.jws.WebService;
    import javax.jws.soap.SOAPBinding;
    import javax.jws.soap.SOAPBinding.Style;

    @WebService(serviceName = "MAPService")
    @SOAPBinding(style = Style.DOCUMENT)
    public class MAPService {
        @WebMethod(operationName = "calculateMAPAndRisk")
        public MAPResult calculateMAPAndRisk(
                @WebParam(name = "systolicPressure") int systolicPressure,
                @WebParam(name = "diastolicPressure") int diastolicPressure) {

            MAPResult result = new MAPResult();
            result.setSystolicPressure(systolicPressure);
            result.setDiastolicPressure(diastolicPressure);

            if (systolicPressure <= 0 || diastolicPressure <= 0 || systolicPressure <= diastolicPressure) {
                result.setCalculatedMAP(0);
                result.setHypertensionRisk("Invalid input: Pressures must be positive and systolic > diastolic.");
                return result;
            }

            double pulsePressure = systolicPressure - diastolicPressure;
            double map = diastolicPressure + (pulsePressure / 3.0);
            double roundedMap = Math.round(map * 100.0) / 100.0;
            result.setCalculatedMAP(roundedMap);

            String riskAssessment;
            String clinicalNotes;

if (roundedMap < 90) {
    riskAssessment = "Normal";
    clinicalNotes = "MAP is less than 90 mmHg, which is considered normal.";
} else if (roundedMap < 92) {
    riskAssessment = "Elevated";
    clinicalNotes = "MAP is between 90 and 92 mmHg, which is considered elevated. Monitor regularly.";
} else if (roundedMap < 96) {
    riskAssessment = "Stage 1 Hypertension";
    clinicalNotes = "MAP is between 92 and 96 mmHg, which is considered Stage 1 Hypertension. Lifestyle changes and monitoring are recommended.";
} else { // roundedMap >= 96
    riskAssessment = "Stage 2 Hypertension";
    clinicalNotes = "MAP is 96 mmHg or higher, which is considered Stage 2 Hypertension. Medical consultation is strongly recommended.";
}

            result.setHypertensionRisk(riskAssessment + ". " + clinicalNotes);
            return result;
        }
    }