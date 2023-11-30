import React, { useState } from "react";
import { Multiselect } from "multiselect-react-dropdown";
import styles from "./Multiselect.module.css"



const MultiselectTest = ({options, placehdr}) => {
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
          placeholder={placehdr}
          selectedValues={selectedOptions}
          className={styles.multiSelectContainer}
        />
      </form>
    </div>
  );
};

export default MultiselectTest;
