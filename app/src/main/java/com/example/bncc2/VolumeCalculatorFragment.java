package com.example.bncc2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class VolumeCalculatorFragment extends Fragment
{
    private EditText editTextDimension1, editTextDimension2, editTextDimension3;
    private LinearLayout layoutVolumeInputs;
    private TextView textViewVolumeResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume_calculator, container, false);

        Spinner spinnerVolumes = view.findViewById(R.id.spinnerVolumes);
        editTextDimension1 = view.findViewById(R.id.editTextDimension1);
        editTextDimension2 = view.findViewById(R.id.editTextDimension2);
        editTextDimension3 = view.findViewById(R.id.editTextDimension3);
        layoutVolumeInputs = view.findViewById(R.id.layoutVolumeInputs);
        Button buttonCalculateVolume = view.findViewById(R.id.buttonCalculateVolume);
        textViewVolumeResult = view.findViewById(R.id.textViewVolumeResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.volumes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVolumes.setAdapter(adapter);

        spinnerVolumes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layoutVolumeInputs.setVisibility(View.VISIBLE);
                String selectedShape = parent.getItemAtPosition(position).toString();
                editTextDimension1.setText("");
                editTextDimension2.setText("");
                editTextDimension3.setText("");  // Clear all fields when changing shapes

                switch (selectedShape) {
                    case "Balok":
                        editTextDimension1.setHint("Length");
                        editTextDimension2.setHint("Width");
                        editTextDimension3.setHint("Height");
                        editTextDimension3.setVisibility(View.VISIBLE);
                        break;
                    case "Piramid":
                        editTextDimension1.setHint("Base Length");
                        editTextDimension2.setHint("Height");
                        editTextDimension3.setVisibility(View.GONE);
                        break;
                    case "Tabung":
                        editTextDimension1.setHint("Radius");
                        editTextDimension2.setHint("Height");
                        editTextDimension3.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                layoutVolumeInputs.setVisibility(View.GONE);
            }
        });

        buttonCalculateVolume.setOnClickListener(v -> {
            double volume = calculateVolume(spinnerVolumes.getSelectedItem().toString(),
                    editTextDimension1.getText().toString(),
                    editTextDimension2.getText().toString(),
                    editTextDimension3.getText().toString());
            textViewVolumeResult.setText(String.format("Volume: %.2f", volume));
        });

        return view;
    }

    private double calculateVolume(String shape, String dim1, String dim2, String dim3) {
        try {
            double dimension1 = Double.parseDouble(dim1);
            double dimension2 = Double.parseDouble(dim2);
            double dimension3 = !dim3.isEmpty() ? Double.parseDouble(dim3) : 0; // Use 0 if dim3 is irrelevant
            double volume = 0.0;

            switch (shape) {
                case "Balok":
                    volume = dimension1 * dimension2 * dimension3;
                    break;
                case "Piramid":
                    // Ensure dimension3 is not used in calculation
                    volume = (dimension1 * dimension1 * dimension2) / 3;
                    break;
                case "Tabung":
                    // Ensure dimension3 is not used in calculation
                    volume = Math.PI * dimension1 * dimension1 * dimension2;
                    break;
            }

            return volume;
        } catch (NumberFormatException e) {
            // Log error or notify user
            return 0.0;
        }
    }
}
