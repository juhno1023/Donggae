import React, { useState, useEffect } from 'react';

const AppliList = ({ title, id, checkedItems, checkedItemHandler }) => {
    const [isChecked, setIsChecked] = useState(null)

    const onCheck = ({ target }) => {
        checkedItemHandler(target.value, target.checked)
        setIsChecked(target.checked)
    }

    useEffect(() => {
        if (checkedItems.includes(title)) {
            setIsChecked(true)
        } else {
            setIsChecked(false)
        }
    }, [checkedItems])

    return (
        <> 
            <div>{id}{title}
            <label>
                <input type='checkbox'
                    name='meal'
                    checked={isChecked}
                    value={title}
                    onChange={e => onCheck(e)}
                    className='hidden' />
            </label>
            </div>
        </>
    );
};

export default AppliList;