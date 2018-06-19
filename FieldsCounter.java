import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FieldsCounter {
	
	Map<Integer, Field> fields;
	
	private final int XSIZE = 5;
	private final int YSIZE = 5;
	
	
	
	private int countBlobSizeForField(Field startingField)
	{
		int colour = startingField.colour, fieldCount = 0;
		Queue<Field> adjacentFieldsOfTheSameColour = new LinkedList<Field>();
		adjacentFieldsOfTheSameColour.add(startingField);
		Queue<Field> adjacentUnusedFields;
		Field field, adjacentField;
		while (!adjacentFieldsOfTheSameColour.isEmpty())
		{
			field = adjacentFieldsOfTheSameColour.remove();
			field.wasUsed = true;
			fieldCount += 1;
			adjacentUnusedFields = findAdjacentUnusedFields(field);
			while (!adjacentUnusedFields.isEmpty())
			{
				adjacentField = adjacentUnusedFields.remove();
				if (adjacentField.colour == colour)
				{
					adjacentField.wasUsed = true;
					adjacentFieldsOfTheSameColour.add(adjacentField);
				}
			}
		}
		return fieldCount;
	}
	
	private int findBiggestBlobOfSameColour()
	{
		int biggestBlobOfSameColour = 0;
		int blobSize = 0;
		int fieldKey = -1;
		Field field;
		for (int row = 0; row < YSIZE; row++)
		{
			for (int col = 0; col < XSIZE; col++)
			{
				fieldKey = calculateHash(row, col);
				field = fields.get(fieldKey);
				if (!field.wasUsed)
				{
					blobSize = countBlobSizeForField(field);
					if (blobSize > biggestBlobOfSameColour)
						biggestBlobOfSameColour = blobSize;
				}
			}
		}
		return biggestBlobOfSameColour;
	}
	
	private Queue<Field> findAdjacentUnusedFields(Field startingField)
	{
		Queue<Field> adjacentUnusedFields = new LinkedList<Field>();
		int xPosition = startingField.xPosition, yPosition = startingField.yPosition;
		addFieldIfUnused(adjacentUnusedFields, xPosition - 1, yPosition);
		addFieldIfUnused(adjacentUnusedFields, xPosition, yPosition - 1);
		addFieldIfUnused(adjacentUnusedFields, xPosition + 1, yPosition);
		addFieldIfUnused(adjacentUnusedFields, xPosition, yPosition + 1);
		return adjacentUnusedFields;
	}
	
	private void addFieldIfUnused(Queue<Field> adjacentUnusedFields, int xPosition, int yPosition)
	{
		Field field;
		int fieldKey = calculateHash(xPosition, yPosition);
		if (fieldKey != -1)
		{
			field = fields.get(fieldKey);
			if (!field.wasUsed) adjacentUnusedFields.add(field);
		}
	}
	
	private int calculateHash(int row, int column)
	{
		if (row < 0 || row >= YSIZE || column < 0 || column >= XSIZE) return -1;
		return row * XSIZE + column;
	}
	
	private void initializeProblem()
	{
		Field field;
		int fieldKey;
		fields = new HashMap<Integer, Field>();
		/*
		for (int row = 0; row < YSIZE; row++)
		{
			for (int col = 0; col < XSIZE; col++)
			{
				field = new Field(row, col, 1, false);
				fieldKey = calculateHash(row, col);
				fields.put(fieldKey, field);
			}
		}*/
		field = new Field(0, 0, 1, false);
		fieldKey = calculateHash(0, 0);
		fields.put(fieldKey, field);
		
		field = new Field(0, 1, 1, false);
		fieldKey = calculateHash(0, 1);
		fields.put(fieldKey, field);
		
		field = new Field(0, 2, 2, false);
		fieldKey = calculateHash(0, 2);
		fields.put(fieldKey, field);
		
		field = new Field(0, 3, 1, false);
		fieldKey = calculateHash(0, 3);
		fields.put(fieldKey, field);
		
		field = new Field(0, 4, 1, false);
		fieldKey = calculateHash(0, 4);
		fields.put(fieldKey, field);
		
		// _____
		
		field = new Field(1, 0, 4, false);
		fieldKey = calculateHash(1, 0);
		fields.put(fieldKey, field);
		
		field = new Field(1, 1, 1, false);
		fieldKey = calculateHash(1, 1);
		fields.put(fieldKey, field);
		
		field = new Field(1, 2, 2, false);
		fieldKey = calculateHash(1, 2);
		fields.put(fieldKey, field);
		
		field = new Field(1, 3, 3, false);
		fieldKey = calculateHash(1, 3);
		fields.put(fieldKey, field);
		
		field = new Field(2, 5, 3, false);
		fieldKey = calculateHash(1, 4);
		fields.put(fieldKey, field);
		
		// ___
		
		field = new Field(2, 0, 4, false);
		fieldKey = calculateHash(2, 0);
		fields.put(fieldKey, field);
		
		field = new Field(2, 1, 3, false);
		fieldKey = calculateHash(2, 1);
		fields.put(fieldKey, field);
		
		field = new Field(2, 2, 2, false);
		fieldKey = calculateHash(2, 2);
		fields.put(fieldKey, field);
		
		field = new Field(2, 3, 3, false);
		fieldKey = calculateHash(2, 3);
		fields.put(fieldKey, field);
		
		field = new Field(2, 4, 3, false);
		fieldKey = calculateHash(2, 4);
		fields.put(fieldKey, field);
		
		// ____
		
		field = new Field(3, 0, 4, false);
		fieldKey = calculateHash(3, 0);
		fields.put(fieldKey, field);
		
		field = new Field(3, 1, 4, false);
		fieldKey = calculateHash(3, 1);
		fields.put(fieldKey, field);
		
		field = new Field(3, 2, 2, false);
		fieldKey = calculateHash(3, 2);
		fields.put(fieldKey, field);
		
		field = new Field(3, 3, 3, false);
		fieldKey = calculateHash(3, 3);
		fields.put(fieldKey, field);
		
		field = new Field(3, 4, 3, false);
		fieldKey = calculateHash(3, 4);
		fields.put(fieldKey, field);
		
		// ___
		
		field = new Field(4, 0, 4, false);
		fieldKey = calculateHash(4, 0);
		fields.put(fieldKey, field);
		
		field = new Field(4, 1, 4, false);
		fieldKey = calculateHash(4, 1);
		fields.put(fieldKey, field);
		
		field = new Field(4, 2, 4, false);
		fieldKey = calculateHash(4, 2);
		fields.put(fieldKey, field);
		
		field = new Field(4, 3, 1, false);
		fieldKey = calculateHash(4, 3);
		fields.put(fieldKey, field);
		
		field = new Field(4, 4, 1, false);
		fieldKey = calculateHash(4, 4);
		fields.put(fieldKey, field);
	}
	
	public static void main(String[] args) {

		FieldsCounter fieldsCounter = new FieldsCounter();
		fieldsCounter.initializeProblem();
		System.out.println("The biggest group of fields with the same colour has " + fieldsCounter.findBiggestBlobOfSameColour() + " fields.");
	}
}
