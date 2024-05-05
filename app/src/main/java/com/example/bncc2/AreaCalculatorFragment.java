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

public class AreaCalculatorFragment extends Fragment {

    private EditText editTextDimension1, editTextDimension2;
    private LinearLayout layoutInputs;
    private TextView textViewResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_calculator, container, false);

        Spinner spinnerShapes = view.findViewById(R.id.spinnerShapes);
        editTextDimension1 = view.findViewById(R.id.editTextDimension1);
        editTextDimension2 = view.findViewById(R.id.editTextDimension2);
        layoutInputs = view.findViewById(R.id.layoutInputs);
        Button buttonCalculate = view.findViewById(R.id.buttonCalculate);
        textViewResult = view.findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.shapes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShapes.setAdapter(adapter);

        spinnerShapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                layoutInputs.setVisibility(View.VISIBLE);
                String selectedShape = parent.getItemAtPosition(position).toString();
                switch (selectedShape) {
                    case "Persegi Panjang":
                        editTextDimension1.setHint("Length");
                        editTextDimension2.setHint("Width");
                        editTextDimension2.setVisibility(View.VISIBLE);
                        break;
                    case "Segitiga":
                        editTextDimension1.setHint("Base");
                        editTextDimension2.setHint("Height");
                        editTextDimension2.setVisibility(View.VISIBLE);
                        break;
                    case "Lingkaran":
                        editTextDimension1.setHint("Radius");
                        editTextDimension2.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                layoutInputs.setVisibility(View.GONE);
            }
        });

        buttonCalculate.setOnClickListener(v -> {
            double area = calculateArea(spinnerShapes.getSelectedItem().toString(),
                    editTextDimension1.getText().toString(),
                    editTextDimension2.getText().toString());
            textViewResult.setText(String.format("Area: %.2f", area));
        });

        return view;
    }

    private double calculateArea(String shape, String dim1, String dim2) {
        try {
            double dimension1 = Double.parseDouble(dim1);
            double dimension2 = !dim2.isEmpty() ? Double.parseDouble(dim2) : 0; // Handle empty dim2 for circle since it only have radius properties
            double area = 0.0;

            switch (shape) {
                case "Persegi Panjang":
                    area = dimension1 * dimension2;
                    break;
                case "Segitiga":
                    area = 0.5 * dimension1 * dimension2;
                    break;
                case "Lingkaran":
                    area = Math.PI * dimension1 * dimension1;
                    break;
                default:
                    break;
            }
            return area;
        } catch (NumberFormatException e) {

            return 0.0;
        }
    }
}
