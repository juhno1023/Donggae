import React, { useState } from "react";
import { Multiselect } from "multiselect-react-dropdown";
import { useNavigate } from "react-router-dom";

const options = [
  { name: "Option 1", id: 1 },
  { name: "Option 2", id: 2 },
  { name: "Option 3", id: 3 },
  { name: "Option 4", id: 4 },
  { name: "Option 5", id: 5 }
];

const MultiselectTest = () => {
  const navigate = useNavigate();
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [removedOptions, setRemovedOptions] = useState([]);
  const onSelectOptions = (selectedList, selectedItem) => {
    setSelectedOptions([...selectedOptions, selectedItem]);
  };
  const onRemoveOptions = (selectedList, removedItem) => {
    setRemovedOptions([...removedOptions, removedItem]);
  };

  return (
    <div>
      <form>
        <Multiselect
          options={options}
          name="particulars"
          onSelect={onSelectOptions}
          onRemove={onRemoveOptions}
          displayValue="name"
          closeIcon="cancel"
          placeholder="Select Options"
          selectedValues={selectedOptions}
          className="multiSelectContainer"
        />
      </form>
      <br />
      <br />
      <button onClick={() => navigate("/random/")}>Go to another page</button>
    </div>
  );
};

export default MultiselectTest;
